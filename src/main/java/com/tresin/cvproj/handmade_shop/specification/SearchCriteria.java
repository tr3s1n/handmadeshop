package com.tresin.cvproj.handmade_shop.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private SearchOperation operation;
	private Object value;

	public SearchCriteria negate() {
		return switch (operation) {
			case EQUAL -> new SearchCriteria(key, SearchOperation.NOT_EQUAL, value);
			case NOT_EQUAL -> new SearchCriteria(key, SearchOperation.EQUAL, value);
			case GREATER_THAN -> new SearchCriteria(key, SearchOperation.LESS_THAN_EQUAL, value);
			case LESS_THAN -> new SearchCriteria(key, SearchOperation.GREATER_THAN_EQUAL, value);
			case GREATER_THAN_EQUAL -> new SearchCriteria(key, SearchOperation.LESS_THAN, value);
			case LESS_THAN_EQUAL -> new SearchCriteria(key, SearchOperation.GREATER_THAN, value);
			case CONTAINS -> new SearchCriteria(key, SearchOperation.NOT_CONTAINS, value);
			case STARTS_WITH -> new SearchCriteria(key, SearchOperation.NOT_STARTS_WITH, value);
			case ENDS_WITH -> new SearchCriteria(key, SearchOperation.NOT_ENDS_WITH, value);
			case IN -> new SearchCriteria(key, SearchOperation.NOT_IN, value);
			case NOT_IN -> new SearchCriteria(key, SearchOperation.IN, value);
			default -> throw new IllegalArgumentException("Unsupported operation: " + operation);
		};
	}
}
