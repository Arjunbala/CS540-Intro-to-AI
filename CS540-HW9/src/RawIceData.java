public class RawIceData {
	private static int[] iceData = {
			118,
			151,
			121,
			96,
			110,
			117,
			132,
			104,
			125,
			118,
			125,
			123,
			110,
			127,
			131,
			99,
			126,
			144,
			136,
			126,
			91,
			130,
			62,
			112,
			99,
			161,
			78,
			124,
			119,
			124,
			128,
			131,
			113,
			88,
			75,
			111,
			97,
			112,
			101,
			101,
			91,
			110,
			100,
			130,
			111,
			107,
			105,
			89,
			126,
			108,
			97,
			94,
			83,
			106,
			98,
			101,
			108,
			99,
			88,
			115,
			102,
			116,
			115,
			82,
			110,
			81,
			96,
			125,
			104,
			105,
			124,
			103,
			106,
			96,
			107,
			98,
			65,
			115,
			91,
			94,
			101,
			121,
			105,
			97,
			105,
			96,
			82,
			116,
			114,
			92,
			98,
			101,
			104,
			96,
			109,
			122,
			114,
			81,
			85,
			92,
			114,
			111,
			95,
			126,
			105,
			108,
			117,
			112,
			113,
			120,
			65,
			98,
			91,
			108,
			113,
			110,
			105,
			97,
			105,
			107,
			88,
			115,
			123,
			118,
			99,
			93,
			96,
			54,
			111,
			85,
			107,
			89,
			87,
			97,
			93,
			88,
			99,
			108,
			94,
			74,
			119,
			102,
			47,
			82,
			53,
			115,
			21,
			89,
			80,
			101,
			95,
			66,
			106,
			97,
			87,
			109,
			57,
			87,
			117,
			91,
			62,
			65,
			94
	};
	
	private static int index = 0;
	
	public static boolean hasNext() {
		return index < iceData.length;
	}
	
	public static Integer getNext() {
		return iceData[index++];
	}
}