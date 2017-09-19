package com.strong.yujiaapp.beanmodel;

import java.io.Serializable;

public class BaiduAuto implements Serializable {

		private static final long serialVersionUID = 1L;
		public int status;
		public BaiduData result;

	public class BaiduData {
		private static final long serialVersionUID = 1L;
		public String formatted_address;
		public LocData location;

	}
	public class LocData {
		private static final long serialVersionUID = 1L;
		public String lat;
		public String lng;
		
	}

}