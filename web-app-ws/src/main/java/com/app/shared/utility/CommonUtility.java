package com.app.shared.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

// common utility to copy list of object from one list to other list
public class CommonUtility <S,T>{
	private  ModelMapper mp = new ModelMapper();
	//copy list of objects with value from source list to target class
	public  List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> mp.map(element, targetClass)).collect(Collectors.toList());
	}
}
