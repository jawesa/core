package com.jawesa.controller.tag;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.primefaces.model.SortOrder;

import com.jawesa.controller.common.EntityLongIdController;
import com.jawesa.model.tag.TagAttribute;

@Stateless
@SuppressWarnings("serial")
public class TagAttributeController extends
		EntityLongIdController<TagAttribute> {
	public List<TagAttribute> getTagAttributes() {
		return createNamedQuery(TagAttribute.FIND_ALL).getResultList();
	}

	public List<TagAttribute> getByCriteria(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
		StringBuffer sbJpql = new StringBuffer(
				"select tagAttribute from TagAttribute as tagAttribute");

		if (filters != null && !filters.isEmpty()) {
			sbJpql.append(" where ");
			for (Entry<String, String> entry : filters.entrySet()) {
				sbJpql.append(entry.getKey().toLowerCase());
				sbJpql.append(" like '");
				sbJpql.append(entry.getValue().toLowerCase());
				sbJpql.append("%' and ");
			}
			sbJpql.delete(sbJpql.length()-5, sbJpql.length());
		}

		if (sortField != null) {
			if (SortOrder.ASCENDING.equals(sortOrder)) {
				sbJpql.append(" order by ").append(sortField).append(" asc");
			} else if (SortOrder.DESCENDING.equals(sortOrder)) {
				sbJpql.append(" order by ").append(sortField).append(" desc");
			}
		}

		Query query = createQuery(sbJpql.toString()).setFirstResult(first)
				.setMaxResults(pageSize);

		return query.getResultList();
	}
}
