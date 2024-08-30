package com.tresin.cvproj.handmade_shop.specification;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductSpecification {
	private final List<SearchCriteria> criteria;

	public ProductSpecification() {
		this.criteria = new ArrayList<>();
	}

	public void addCriteria(SearchCriteria criteria) {
		this.criteria.add(criteria);
	}

	public boolean hasCriteria() {
		return !criteria.isEmpty();
	}

	public ProductSpecification and(ProductSpecification other) {
		ProductSpecification combined = new ProductSpecification();
		combined.getCriteria().addAll(this.criteria);
		combined.getCriteria().addAll(other.criteria);
		return combined;
	}

	public ProductSpecification or(ProductSpecification other) {
		ProductSpecification combined = new ProductSpecification();
		combined.getCriteria().addAll(this.criteria);
		combined.getCriteria().addAll(other.criteria);
		return combined;
	}

	public ProductSpecification not() {
		ProductSpecification negated = new ProductSpecification();
		for (SearchCriteria criteria : this.criteria) {
			negated.addCriteria(criteria.negate());
		}
		return negated;
	}

}
