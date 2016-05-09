/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.client;
 
import java.lang.reflect.ParameterizedType; 
import java.lang.reflect.Type; 
 
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-10-20T11:29:47.599-07:00") 
public class TypeRef<T> { 
    private final Type type; 
 
    public TypeRef() { 
        this.type = getGenericType(getClass()); 
    } 
 
    private static Type getGenericType(Class<?> klass) { 
        Type superclass = klass.getGenericSuperclass(); 
        if (superclass instanceof Class) { 
            throw new RuntimeException("No type parameter provided"); 
        } 
        ParameterizedType parameterized = (ParameterizedType) superclass; 
        return parameterized.getActualTypeArguments()[0]; 
    } 
 
    public Type getType() { 
        return type; 
    } 
}