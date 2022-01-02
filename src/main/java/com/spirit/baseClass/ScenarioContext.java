package com.spirit.baseClass;

import java.util.HashMap;
import java.util.Map;

import com.spirit.enums.Context;



public class ScenarioContext{
	
	private  Map<String, Object> scenarioContext;
	 
    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(Context key){
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(Context key){
    	boolean flag = scenarioContext.containsKey(key.toString());
        return flag;
    }

}
