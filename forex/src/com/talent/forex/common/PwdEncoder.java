package com.talent.forex.common;

public interface PwdEncoder {
	/**
	 * �������
	 * 
	 * @param rawPass
	 *            δ��������
	 * @return ���ܺ�����
	 */
	public String encodePassword(String rawPass);

	/**
	 * ��֤�����Ƿ���ȷ
	 * 
	 * @param encPass
	 *            ��������
	 * @param rawPass
	 *            δ��������
	 * @return
	 */
	public boolean isPasswordValid(String encPass, String rawPass);
}
