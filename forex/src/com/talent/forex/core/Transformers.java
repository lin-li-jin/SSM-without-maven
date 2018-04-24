package com.talent.forex.core;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.ResultTransformer;

public final class Transformers
{
  public static final ResultTransformer ALIAS_TO_ENTITY_MAP = new AliasToEntityMapResultTransformer();

  public static final ResultTransformer TO_LIST = ToListResultTransformer.INSTANCE;

  public static ResultTransformer aliasToBean(Class target)
  {
    return new AliasToBeanResultTransformer(target);
  }
}
