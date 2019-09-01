package com.kgc.kh76.soso.service;

import com.kgc.kh76.soso.entity.MobileCard;

public interface CallService {
	
	/**
	 * 
	 * @param minCount	
	 * @param card			¿¨
	 * @return
	 */
	public int call(int minCount,MobileCard card); 
}
