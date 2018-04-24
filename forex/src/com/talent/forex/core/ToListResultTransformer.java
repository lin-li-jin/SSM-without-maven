package com.talent.forex.core;

import java.util.Arrays;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

public class ToListResultTransformer
  implements ResultTransformer
{
  public static final ResultTransformer INSTANCE = new ToListResultTransformer();

  public Object transformTuple(Object[] tuple, String[] aliases)
  {
    return Arrays.asList(tuple);
  }

  public List transformList(List collection) {
    return collection;
  }
}
