package by.epamjwd.mobile.controller.repository;

public class IndexRepository {
	
	private IndexRepository() {
	}

	//news articles indexes
	public final static int IDX_SHIFT = 1; 
	public final static int NULL_INDEX = 0;
	public final static int STEP = 10;

	//data storage IDs 
	//(hopefully it is allowed to combine indexes and IDs in one container class)
	public final static long ERROR_ID = -1L;
	public final static long EMPTY_ID = 0L;

}
