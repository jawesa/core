package com.jawesa.action.tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.jawesa.controller.common.AbstractMutable;
import com.jawesa.model.tag.JsMethod;
import com.jawesa.model.tag.Library;
import com.jawesa.model.tag.Tag;
import com.jawesa.model.tag.TagApi;
import com.jawesa.model.tag.TagAttribute;
import com.jawesa.model.tag.TagDemo;
import com.jawesa.model.tag.TagDemoType;
import com.jawesa.util.cache.ApplicationCache;

@Named
@ConversationScoped
@SuppressWarnings("serial")
public class TagDisplayAction extends AbstractMutable {
	// URL params
	private String libraryParam; // required
	private String tagParam; // required
	private String demoParam;

	// Main attributes (same for all demos)
	private boolean mainDirty = true;
	private Library library;
	private Tag tag;
	private MenuModel demosMenuModel;

	// View related attributes
	
	private TagDemo demo;
	private List<TagAttribute> attributes;
	private List<JsMethod> jsMethods;
	
	@Inject
	private ApplicationCache applicationCache;

	// Force all refresh
	public void refresh() {
		setMainDirty();
		checkRefresh();
	}

	// What if the user change locale ?
	public void changeLocale(@Observes Locale locale) {
		if(tag != null) {
			refreshDemosMenuModel(locale);
		}
	}

	// Check if refresh required and do it if need
	private void checkRefresh() {
		if (cleanMainDirty()) {
			refreshMain();
		}

		if (clearDirty()) {
			refreshView();
		}
	}

	private void refreshMain() {
		//conversationAction.begin();
		
		if (libraryParam != null && tagParam != null) {
			//tag = tagController.getTagByLibraryAndName(libraryParam, tagParam);
			Long tagId = applicationCache.getTagsAltKeys().get(Tag.getAltKey(libraryParam, tagParam));
			tag = applicationCache.getTags().get(tagId);
		} else {
			tag = null;
		}

		if (tag != null) {
			//library = tag.getLibrary();
			Long libraryId = applicationCache.getLibrariesAltKeys().get(Library.getAltKey(libraryParam));
			library = applicationCache.getLibraries().get(libraryId);
			refreshDemosMenuModel(null);
		} else {
			library = null;
			demosMenuModel = null;
		}

		setDirty();
		demo = null;
		attributes = null;
		jsMethods = null;
	}

	private void refreshView() {
		TagDemoType demoType = getDemoType();

		if (TagDemoType.DOCUMENTATION.equals(demoType)) {
			refreshDocumentation();
		} else if (TagDemoType.API.equals(demoType)) {
			refreshApi();
		} else if (TagDemoType.JAVASCRIPT.equals(demoType)) {
			refreshJavascript();
		} else {
			refreshDemo();
		}
	}

	private void refreshDemosMenuModel(Locale locale) {
//		String jpql = "select distinct demo.slug, demo.nameLabel from Tag as tag join tag.demos as demo where tag = :tag order by demo.slug";
//		List<Object[]> demos = tagController.createQuery(jpql)
//				.setParameter("tag", tag).getResultList();

		MenuModel demosMenuModel = new DefaultMenuModel();
		StringBuffer sb;

//		for (Object[] demo : demos) {
		for(TagDemo demo : tag.getDemos()) {
			MenuItem item = new MenuItem();
			sb = new StringBuffer();
			sb.append("/public/tag/display.xhtml?library=").append(libraryParam)
					.append("&tag=").append(tagParam).append("&demo=")
					.append(demo.getSlug());
//					.append(demo[0].toString());
			item.setUrl(sb.toString());
			
			String name;
			if(locale == null) {
//				name = ((I18nLabel) demo[1]).getValue();
				name = demo.getNameLabel().getValue();
			} else {
//				name = ((I18nLabel) demo[1]).getValue(locale);
				name = demo.getNameLabel().getValue(locale);
			}
			
			item.setValue(name);

			demosMenuModel.addMenuItem(item);
		}

		this.demosMenuModel = demosMenuModel;
	}

	public TagDemoType getDemoType() {
		if (demoParam == null
				|| TagDemoType.CLASSIC.name().equalsIgnoreCase(demoParam)) {
			return TagDemoType.DOCUMENTATION;
		} else {
			try {
				TagDemoType demoType = TagDemoType.valueOf(demoParam.toUpperCase());
				return demoType;
			} catch (IllegalArgumentException iae) {
				return TagDemoType.CLASSIC;
			}
		}
	}

	public String getMenuIndex() {
		return (getTag() == null ? "0" : getTag().getType().getMenuOrder() + "");
	}

	public boolean hasDemos() {
		MenuModel demosMenuModel = getDemosMenuModel();
		return (demosMenuModel != null && demosMenuModel.getContents() != null && !demosMenuModel
				.getContents().isEmpty());
	}

	private void refreshDemo() {
//		String jpql = "select tagDemo from Tag as tag join tag.demos as tagDemo where tag = :tag and tagDemo.slug = :slug";
//		TagDemo demo = (TagDemo) tagController.createQuery(jpql)
//				.setParameter("tag", tag).setParameter("slug", demoParam)
//				.getSingleResult();
		
		TagDemo demo = applicationCache.getTagDemo(libraryParam, tagParam, demoParam);

		this.demo = demo;
	}

	private void refreshApi() {
		if (this.attributes == null) {
//			String jpql = "select distinct tagAttribute from Tag as tag join tag.attributes as tagAttribute where tag = :tag";
//			List<TagAttribute> attributes = tagController.createQuery(jpql)
//					.setParameter("tag", tag).getResultList();
			
			Set<TagAttribute> setAttributes = new HashSet<TagAttribute>();
			
			for(TagApi api : tag.getApis()) {
				setAttributes.addAll(api.getAttributes());
			}
			
			setAttributes.addAll(tag.getAttributes());

			this.attributes = new ArrayList<TagAttribute>(setAttributes);
		}
	}

	private void refreshDocumentation() {

	}

	private void refreshJavascript() {
		if (this.jsMethods == null) {
//			String jpql = "select distinct jsMethod from Tag as tag join tag.jsMethods as jsMethod where tag = :tag";
//			List<JsMethod> jsMethods = tagController.createQuery(jpql)
//					.setParameter("tag", tag).getResultList();

			List<JsMethod> jsMethods = new ArrayList<JsMethod>(tag.getJsMethods());
			
			this.jsMethods = jsMethods;
		}
	}

	private void setMainDirty(String oldValue, String newValue) {
		mainDirty = mainDirty
				|| (oldValue != newValue && (oldValue == null || !oldValue
						.equals(newValue)));
	}

	private boolean cleanMainDirty() {
		boolean result = mainDirty;
		mainDirty = false;
		return result;
	}

	private void setMainDirty() {
		mainDirty = true;
	}

	public String getLibraryParam() {
		return libraryParam;
	}

	public void setLibraryParam(String libraryParam) {
		setMainDirty(this.libraryParam, libraryParam);
		this.libraryParam = libraryParam;
	}

	public String getTagParam() {
		return tagParam;
	}

	public void setTagParam(String tagParam) {
		setMainDirty(this.tagParam, tagParam);
		this.tagParam = tagParam;
	}

	public String getDemoParam() {
		return demoParam;
	}

	public void setDemoParam(String demoParam) {
		setDirty(this.demoParam, demoParam);
		this.demoParam = demoParam;
	}

	public Library getLibrary() {
		checkRefresh();
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Tag getTag() {
		checkRefresh();
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public TagDemo getDemo() {
		checkRefresh();
		return demo;
	}

	public void setDemo(TagDemo demo) {
		this.demo = demo;
	}

	public List<TagAttribute> getAttributes() {
		checkRefresh();
		return attributes;
	}

	public void setAttributes(List<TagAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<JsMethod> getJsMethods() {
		checkRefresh();
		return jsMethods;
	}

	public void setJsMethods(List<JsMethod> jsMethods) {
		this.jsMethods = jsMethods;
	}

	public MenuModel getDemosMenuModel() {
		checkRefresh();
		return demosMenuModel;
	}

}
