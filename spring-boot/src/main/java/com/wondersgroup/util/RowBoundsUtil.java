package com.wondersgroup.util;

import org.apache.ibatis.session.RowBounds;

/**
 * 继承 mybatis 分页 RowBounds
 * @author chen
 *
 */
public class RowBoundsUtil extends RowBounds {

	public RowBoundsUtil(int offset, int limit) {
		super((offset - 1) * limit, limit);
	}
}
