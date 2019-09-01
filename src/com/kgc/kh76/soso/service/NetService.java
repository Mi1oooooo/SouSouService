package com.kgc.kh76.soso.service;

import com.kgc.kh76.soso.entity.MobileCard;

public interface NetService {
	//上网 netPlay(flow:int, card:MobileCard):int
	/**
	 * 
	 * @param flow 	上网流量
	 * @param card	卡
	 * @return
	 */
	public int netPlay(int flow,MobileCard card);
}
