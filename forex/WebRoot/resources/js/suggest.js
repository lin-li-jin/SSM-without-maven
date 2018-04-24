

//-------------------- mContentLoader.js
var net = new Object();
/*定义请求的状态*/
net.READY_STATE_UNINITIALIZED= 0;
net.READY_STATE_LOADING      = 1;
net.READY_STATE_LOADED       = 2;
net.READY_STATE_INTERACTIVE  = 3;
net.READY_STATE_COMPLETE     = 4;

net.ContentLoader = function( component, url, method, requestParams ) {
   this.component     = component;
   this.url           = url;
   this.requestParams = requestParams;
   this.method        = method;
}

net.ContentLoader.prototype = {

   getTransport: function() {
      var transport;
      if ( window.XMLHttpRequest )
         transport = new XMLHttpRequest();
      else if ( window.ActiveXObject ) {
         try {
            transport = new ActiveXObject('Msxml2.XMLHTTP');
         }
         catch(err) {
            transport = new ActiveXObject('Microsoft.XMLHTTP');
         }
      }
      return transport;
   },

   sendRequest: function() {

      //if ( window.netscape && window.netscape.security.PrivilegeManager.enablePrivilege)
      //   netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');

      var requestParams = []
      for ( var i = 0 ; i < arguments.length ;  i++ )
         requestParams.push(arguments[i]);

      var oThis = this;
      var request = this.getTransport();
      request.open( this.method, this.url, true );
      request.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded');
      request.onreadystatechange = function() { oThis.handleAjaxResponse(request) };
      request.send( this.queryString(requestParams) );
  },

  queryString: function(args) {

     var requestParams = [];
     for ( var i = 0 ; i < this.requestParams.length ; i++ )
        requestParams.push(this.requestParams[i]);
     for ( var j = 0 ; j < args.length ; j++ )
        requestParams.push(args[j]);

     var queryString = "";
     if ( requestParams && requestParams.length > 0 ) {
        for ( var i = 0 ; i < requestParams.length ; i++ )
           queryString += requestParams[i] + '&';
        queryString = queryString.substring(0, queryString.length-1);
     }
     return queryString;
  },
  /*用于处理ajax回应的事件*/
  handleAjaxResponse: function(request) {
     if ( request.readyState == net.READY_STATE_COMPLETE ) {
        if ( this.isSuccess(request) )
           this.component.ajaxUpdate(request);
        else
           this.component.handleError(request);
     }
  },

  isSuccess: function(request) {
    return  request.status == 0
        || (request.status >= 200 && request.status < 300);
  }

};


//-------------------- mTextSuggest.js

TextSuggest = Class.create();

TextSuggest.prototype = {
   /*初始化函数*/
   /*anId    ——  将被提示的输入框*/
   /*url     ——  向服务器请求的动作*/
   /*options ——  输入框的一些个性化定制*/
   initialize: function(anId, url, options) {

      this.id          = anId;
      var browser = navigator.userAgent.toLowerCase();
      this.isIE        = browser.indexOf("msie") != -1;
      this.isOpera     = browser.indexOf("opera")!= -1;
      this.textInput   = $(this.id);
      this.suggestions = [];
      this.oldSug      = {};
      this.haveSaveSug = 0;
      this.setOptions(options);
      this.initAjax(url);

      this.injectSuggestBehavior();
   },
   /*初始化ajaxEngine对象，利用ajaxEngine发出请求*/
   initAjax: function(url) {
      ajaxEngine.registerRequest( this.id + '_request', url );
      ajaxEngine.registerAjaxObject( this.id + '_updater', this );
   },
   /*设置个性化定制，如没有则给予默认值*/
   setOptions: function(options) {
      this.options = {
         suggestDivClassName: 'suggestDiv',   /*显示提示框div的css类*/
         suggestionClassName: 'suggestion',   /*显示提示框span的css类*/
         matchClassName     : 'match',        /*显示匹配文本的css类*/
         valueClassName     : 'valueShow',    /*显示值的css类*/
         matchTextWidth     : true,           /*提示框自动适应匹配文本的长度*/
         selectionColor     : '#b1c09c',      /*被选中项目的颜色*/
         matchAnywhere      : false,          /*是否任意地方匹配*/
         ignoreCase         : false,          /*是否忽略大小写*/
         count              : 10,             /*提示框的显示记录个数*/
         showType           : 'valueF',       /*选定时候输入框显示的内容*/
                                              /*text   —— 可见文本*/
                                              /*value  —— 值*/
                                              /*textF  —— 可见文本前*/
                                              /*valueF —— 值前*/
         showSplit          : '-',            /*showType为textF或valueF前时有用,用于间隔text和value*/
         isRefresh          : false,          /*输入相同的值的时候是否向服务器刷新*/
         saveCount          : 10              /*保存旧的数据达到了这个数目后，便开始清空*/
      }.extend(options || {});
   },
   /*重置选项，可以让提示的值给予变动*/
   resetOptions: function(options) {
      this.options = this.options.extend(options || {});
      this.oldSug      = {};
      this.haveSaveSug = 0;
      this.suggestions = [];
      this.lastRequestString = this.textInput.value;
      this.updateSuggestionsDiv();
   },
   /*初始化提示框：建立事件关联，创建提示框用到的控件*/
   injectSuggestBehavior: function() {

      if ( this.isIE )
         this.textInput.autocomplete = "off";

      var keyEventHandler = new TextSuggestKeyHandler(this);
      new Insertion.After( this.textInput,
                           '<input type="text" id="'+this.id+'_preventtsubmit'+'" style="display:none"/>' );
      new Insertion.After( this.textInput,
                           '<input type="hidden" name="'+this.id+'_hidden'+'" id="'+this.id+'_hidden'+'"/>' );

      this.createSuggestionsDiv();
   },
   /*处理用户的键盘输入事件*/
   handleTextInput: function() {
     var previousRequest    = this.lastRequestString;
     this.lastRequestString = this.textInput.value;
     if ( this.lastRequestString == "" )
        this.hideSuggestions();
     else if ( this.lastRequestString != previousRequest ) {
        this.sendRequestForSuggestions();
     }
   },
   /*处理用户输入向上键事件*/
   moveSelectionUp: function() {
      if ( this.selectedIndex > 0 ) {
         this.updateSelection(this.selectedIndex - 1);
      }
   },
   /*处理用户输入向下键事件*/
   moveSelectionDown: function() {
      if ( this.selectedIndex < (this.suggestions.length - 1)  ) {
         this.updateSelection(this.selectedIndex + 1);
      }
   },
   /*更新第n条提示的记录（显示为被选中）*/
   updateSelection: function(n) {
      var span = $( this.id + "_" + this.selectedIndex );
      if ( span ){
         span.style.backgroundColor = "";
      }
      this.selectedIndex = n;
      var span = $( this.id + "_" + this.selectedIndex );
      if ( span ){
         span.style.backgroundColor = this.options.selectionColor;
      }
   },
   /*发送更新提示信息的申请*/
   sendRequestForSuggestions: function() {
     //先判断是否需要向服务器更新
     if(!this.options.isRefresh){
       var oldSuggestions = this.oldSug[this.lastRequestString];
       if(oldSuggestions){
         this.suggestions = oldSuggestions;
         //this.suggestions = [];
         //for( var i = 0 ; i < oldSuggestions.length ; i++ ) {
         //   this.suggestions.push({ text: oldSuggestions[i].text, value: oldSuggestions[i].value });
         //}
         this.updateSuggestionsDiv();
         this.showSuggestions();
         this.updateSelection(0);
         return;
       }
     }
     
     if ( this.handlingRequest ) {
        this.pendingRequest = true;
        return;
     }

     this.handlingRequest = true;
     this.callRicoAjaxEngine();
   },
   /*真正利用ajaxEngin向服务器发送消息的动作*/
   callRicoAjaxEngine: function() {
      var callParms = [];
      callParms.push( this.id + '_request');
      callParms.push( 'id='             + this.id);
      callParms.push( 'count='          + this.options.count);
      callParms.push( 'query='          + this.lastRequestString);
      callParms.push( 'matchAnywhere=' + this.options.matchAnywhere);
      callParms.push( 'ignoreCase='    + this.options.ignoreCase);

      var additionalParms = this.options.requestParameters || [];
      for( var i=0 ; i < additionalParms.length ; i++ ){
         callParms.push(additionalParms[i]);
      }
      ajaxEngine.sendRequest.apply( ajaxEngine, callParms );
   },
   /*当ajax有回应的时候更新提示框，被ajaxEngine或者net.ContentLoader调用*/
   /*这个函数可以说是实现ajaxEngine或者net.ContentLoader类的接口*/
   ajaxUpdate: function( ajaxResponse ) {
      this.createSuggestions( ajaxResponse );

      if ( this.suggestions.length == 0 ) {
         this.hideSuggestions();
         $( this.id + "_hidden" ).value = "";
      }
      else {
         this.updateSuggestionsDiv();
         this.showSuggestions();
         this.updateSelection(0);
      }

      this.handlingRequest = false;

      if ( this.pendingRequest ) {
         this.pendingRequest    = false;
         this.lastRequestString = this.textInput.value;
         this.sendRequestForSuggestions();
      }
   },
   /*利用回应ajaxResponse，进行创建提示框里面的内容*/
   createSuggestions: function(ajaxResponse) {
      this.suggestions = [];
      var newSug = [];
      var entries = ajaxResponse.getElementsByTagName('entry');
      for ( var i = 0 ; i < entries.length ; i++ ) {
         var strText  = this.getElementContent(entries[i].getElementsByTagName('text')[0]);
         var strValue = this.getElementContent(entries[i].getElementsByTagName('value')[0]);
         this.suggestions.push( { text: strText, value: strValue } );
         newSug.push( { text: strText, value: strValue } );
      }
      if( !this.options.isRefresh && this.suggestions.length != 0 ) {
         if( this.haveSaveSug >= this.options.saveCount) {
            this.oldSug = {};
            this.haveSaveSug = 0;
         }
         this.oldSug[this.lastRequestString] = newSug;
         this.haveSaveSug ++;
      }
   },
   /*将输入框的值置为输入提示框的中选中的值*/
   setInputFromSelection: function() {
     var hiddenInput = $( this.id + "_hidden" );
     var suggestion  = this.suggestions[ this.selectedIndex ];

      if( this.options.showType == 'text' || this.options.showType == 'textF' ){
         this.textInput.value = suggestion.text;
      } else {
         this.textInput.value = suggestion.value;
      }
     hiddenInput.value    = suggestion.value;
     this.hideSuggestions();
   },
   /*显示提示框操作*/
   showSuggestions: function() {
      if ( this.suggestions.length == 0 || this.lastRequestString == "" ) {
         return;
      }
      var divStyle = this.suggestionsDiv.style;
      if ( divStyle.display == '' )
         return;
      this.positionSuggestionsDiv();
      divStyle.display = '';
   },
   /*定位提示框的绝对位置*/
   positionSuggestionsDiv: function() {
      var textPos = RicoUtil.toDocumentPosition(this.textInput);
      var divStyle = this.suggestionsDiv.style;
      divStyle.top  = (textPos.y + this.textInput.offsetHeight) + "px";
      divStyle.left = textPos.x + "px";

      if ( this.options.matchTextWidth )
         divStyle.width = (this.textInput.offsetWidth- this.padding()) + "px";
   },
   /*填充用于提示的div*/
   padding: function() {
     try{
      var styleFunc = RicoUtil.getElementsComputedStyle;
      var lPad    = styleFunc( this.suggestionsDiv, "paddingLeft",      "padding-left" );
      var rPad    = styleFunc( this.suggestionsDiv, "paddingRight",     "padding-right" );
      var lBorder = styleFunc( this.suggestionsDiv, "borderLeftWidth",  "border-left-width" );
      var rBorder = styleFunc( this.suggestionsDiv, "borderRightWidth", "border-right-width" );

      lPad    = isNaN(lPad)    ? 0 : lPad;
      rPad    = isNaN(rPad)    ? 0 : rPad;
      lBorder = isNaN(lBorder) ? 0 : lBorder;
      rBorder = isNaN(rBorder) ? 0 : rBorder;

      return parseInt(lPad) + parseInt(rPad) + parseInt(lBorder) + parseInt(rBorder);
     }catch (e){
      return 0;
     }
   },
   /*隐藏提示框操作*/
   hideSuggestions: function() {
      this.suggestionsDiv.style.display = 'none';
   },
   /*创建用于提示框的div节点*/
   createSuggestionsDiv: function() {
      this.suggestionsDiv = document.createElement("div");
      this.suggestionsDiv.className = this.options.suggestDivClassName;

      var divStyle = this.suggestionsDiv.style;
      divStyle.position = 'absolute';
      divStyle.zIndex   = 101;
      divStyle.display  = "none";

      this.textInput.parentNode.appendChild(this.suggestionsDiv);
   },
   /*更新提示框里面的提示信息*/
   updateSuggestionsDiv: function() {
      this.suggestionsDiv.innerHTML = "";
      var suggestLines = this.createSuggestionSpans();
      for ( var i = 0 ; i < suggestLines.length ; i++ ) {
         this.suggestionsDiv.appendChild(suggestLines[i]);
      }
   },
   /*创建用于提示的span*/
   createSuggestionSpans: function() {
      var regExpFlags = "";
      if ( this.options.ignoreCase )
         regExpFlags = 'i';
      var startRegExp = "^";
      if ( this.options.matchAnywhere )
         startRegExp = '';

      var regExp  = new RegExp( startRegExp + this.lastRequestString, regExpFlags );

      var suggestionSpans = [];
      if( this.options.showType == 'text' ) {
         for ( var i = 0 ; i < this.suggestions.length ; i++ )
            suggestionSpans.push( this.createSuggestionSpanText( i, regExp ) )
      } else if( this.options.showType == 'value' ) {
         for ( var i = 0 ; i < this.suggestions.length ; i++ )
            suggestionSpans.push( this.createSuggestionSpanValue( i, regExp ) )
      } else if( this.options.showType == 'textF' ) {
         for ( var i = 0 ; i < this.suggestions.length ; i++ )
            suggestionSpans.push( this.createSuggestionSpanTextF( i, regExp ) )
      } else {
         for ( var i = 0 ; i < this.suggestions.length ; i++ )
            suggestionSpans.push( this.createSuggestionSpanValueF( i, regExp ) )
      }

      return suggestionSpans;
   },
   /*创建一个用于提示的span*//*只显示text*/
   createSuggestionSpanText: function( n, regExp ) {
      var suggestion = this.suggestions[n];

      var suggestionSpan = document.createElement("span");
      suggestionSpan.className = this.options.suggestionClassName;
      suggestionSpan.style.width   = '100%';
      suggestionSpan.style.display = 'block';
      suggestionSpan.id            = this.id + "_" + n;
      suggestionSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      suggestionSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      var textValues = this.splitTextValues( suggestion.text,
                                             this.lastRequestString.length,
                                             regExp );

      var textMatchSpan = document.createElement("span");
      textMatchSpan.id            = this.id + "_match_" + n;
      textMatchSpan.className     = this.options.matchClassName;
      textMatchSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      textMatchSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      textMatchSpan.appendChild( document.createTextNode(textValues.mid) );

      suggestionSpan.appendChild( document.createTextNode( textValues.start ) );
      suggestionSpan.appendChild( textMatchSpan );
      suggestionSpan.appendChild( document.createTextNode( textValues.end ) );

      return suggestionSpan;
   },
   /*创建一个用于提示的span*//*只显示Value*/
   createSuggestionSpanValue: function( n, regExp ) {
      var suggestion = this.suggestions[n];

      var suggestionSpan = document.createElement("span");
      suggestionSpan.className = this.options.suggestionClassName;
      suggestionSpan.style.width   = '100%';
      suggestionSpan.style.display = 'block';
      suggestionSpan.id            = this.id + "_" + n;
      suggestionSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      suggestionSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      var valueSpan = document.createElement("span");
      valueSpan.id                 = this.id + "_value_" + n;
      valueSpan.className          = this.options.valueClassName;
      valueSpan.onmouseover        = this.mouseoverHandler.bindAsEventListener(this);
      valueSpan.onclick            = this.itemClickHandler.bindAsEventListener(this);
      
      valueSpan.appendChild( document.createTextNode( suggestion.value ) );
      
      suggestionSpan.appendChild( valueSpan );

      return suggestionSpan;
   },
   /*创建一个用于提示的span*//*同时显示text和value，不过text位于前*/
   createSuggestionSpanTextF: function( n, regExp ) {
      var suggestion = this.suggestions[n];

      var suggestionSpan = document.createElement("span");
      suggestionSpan.className = this.options.suggestionClassName;
      suggestionSpan.style.width   = '100%';
      suggestionSpan.style.display = 'block';
      suggestionSpan.id            = this.id + "_" + n;
      suggestionSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      suggestionSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      var textValues = this.splitTextValues( suggestion.text,
                                             this.lastRequestString.length,
                                             regExp );

      var textMatchSpan = document.createElement("span");
      textMatchSpan.id            = this.id + "_match_" + n;
      textMatchSpan.className     = this.options.matchClassName;
      textMatchSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      textMatchSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      textMatchSpan.appendChild( document.createTextNode(textValues.mid) );
      
      var valueSpan = document.createElement("span");
      valueSpan.id                 = this.id + "_value_" + n;
      valueSpan.className          = this.options.valueClassName;
      valueSpan.onmouseover        = this.mouseoverHandler.bindAsEventListener(this);
      valueSpan.onclick            = this.itemClickHandler.bindAsEventListener(this);
      
      valueSpan.appendChild( document.createTextNode( suggestion.value ) );

      suggestionSpan.appendChild( document.createTextNode( textValues.start ) );
      suggestionSpan.appendChild( textMatchSpan );
      suggestionSpan.appendChild( document.createTextNode( textValues.end ) );
      suggestionSpan.appendChild( document.createTextNode( this.options.showSplit ) );
      suggestionSpan.appendChild( valueSpan );

      return suggestionSpan;
   },
   /*创建一个用于提示的span*//*同时显示text和value，不过values位于前*/
   createSuggestionSpanValueF: function( n, regExp ) {
      var suggestion = this.suggestions[n];

      var suggestionSpan = document.createElement("span");
      suggestionSpan.className = this.options.suggestionClassName;
      suggestionSpan.style.width   = '100%';
      suggestionSpan.style.display = 'block';
      suggestionSpan.id            = this.id + "_" + n;
      suggestionSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      suggestionSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      var textValues = this.splitTextValues( suggestion.text,
                                             this.lastRequestString.length,
                                             regExp );

      var textMatchSpan = document.createElement("span");
      textMatchSpan.id            = this.id + "_match_" + n;
      textMatchSpan.className     = this.options.matchClassName;
      textMatchSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      textMatchSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      textMatchSpan.appendChild( document.createTextNode(textValues.mid) );
      
      var valueSpan = document.createElement("span");
      valueSpan.id                 = this.id + "_value_" + n;
      valueSpan.className          = this.options.valueClassName;
      valueSpan.onmouseover        = this.mouseoverHandler.bindAsEventListener(this);
      valueSpan.onclick            = this.itemClickHandler.bindAsEventListener(this);
      
      valueSpan.appendChild( document.createTextNode( suggestion.value ) );

      suggestionSpan.appendChild( valueSpan );
      suggestionSpan.appendChild( document.createTextNode( this.options.showSplit ) );
      suggestionSpan.appendChild( document.createTextNode( textValues.start ) );
      suggestionSpan.appendChild( textMatchSpan );
      suggestionSpan.appendChild( document.createTextNode( textValues.end ) );

      return suggestionSpan;
   },
   /*提示框中处理鼠标移动时的变化事件*/
   mouseoverHandler: function(e) {
      var src = e.srcElement ? e.srcElement : e.target;
      var index = parseInt(src.id.substring(src.id.lastIndexOf('_')+1));
      this.updateSelection(index);
   },
   /*显示项的鼠标点击事件处理*/
   itemClickHandler: function(e) {
      this.mouseoverHandler(e);
      this.setInputFromSelection();
      this.hideSuggestions();
      this.textInput.focus();
   },
   /*将显示的字符串分为匹配前，匹配，匹配后的三个字符串（估计用于显示的不同）*/
   splitTextValues: function( text, len, regExp ) {
      var startPos  = text.search(regExp);
      var matchText = text.substring( startPos, startPos + len );
      var startText = startPos == 0 ? "" : text.substring(0, startPos);
      var endText   = text.substring( startPos + len );
      return { start: startText, mid: matchText, end: endText };
   },
   /*获取节点element中的第一个孩子的值*/
   getElementContent: function(element) {
      return element.firstChild.data;
   }
}

/*提示事件关联类*/
TextSuggestKeyHandler = Class.create();

TextSuggestKeyHandler.prototype = {

   initialize: function( textSuggest ) {
      this.textSuggest = textSuggest;
      this.input       = this.textSuggest.textInput;
      this.addKeyHandling();
   },

   addKeyHandling: function() {
      this.input.onkeyup    = this.keyupHandler.bindAsEventListener(this);
      this.input.onkeydown  = this.keydownHandler.bindAsEventListener(this);
      this.input.onblur     = this.onblurHandler.bindAsEventListener(this);
      this.input.onfocus    = this.onfocusHandler.bindAsEventListener(this);
      if ( this.isOpera )
         this.input.onkeypress = this.keyupHandler.bindAsEventListener(this);
   },

   keydownHandler: function(e) {
      var upArrow   = 38;
      var downArrow = 40;

      if ( e.keyCode == upArrow ) {
         this.textSuggest.moveSelectionUp();
         setTimeout( this.moveCaretToEnd.bind(this), 1 );
      }
      else if ( e.keyCode == downArrow ){
         this.textSuggest.moveSelectionDown();
      }
   },

   keyupHandler: function(e) {
      if ( this.input.length == 0 && !this.isOpera )
         this.textSuggest.hideSuggestions();

     if ( !this.handledSpecialKeys(e) )
        this.textSuggest.handleTextInput();
   },

   handledSpecialKeys: function(e) {
      var enterKey  = 13;
      var upArrow   = 38;
      var downArrow = 40;

      if ( e.keyCode == upArrow || e.keyCode == downArrow ) {
         return true;
      }
      else if ( e.keyCode == enterKey ) {
         this.textSuggest.setInputFromSelection();
         return true;
      }

      return false;
   },

   moveCaretToEnd: function() {
      var pos = this.input.value.length;
      if (this.input.setSelectionRange) {
         this.input.setSelectionRange(pos,pos);
      }
      else if(this.input.createTextRange){
         var m = this.input.createTextRange();
         m.moveStart('character',pos);
         m.collapse();
         m.select();
      }
   },

   onblurHandler: function(e) {
      if ( this.textSuggest.suggestionsDiv.style.display == '' )
         this.textSuggest.setInputFromSelection();
      this.textSuggest.hideSuggestions();
   },
   
   onfocusHandler: function(e) {
      this.textSuggest.showSuggestions();
   }

};

