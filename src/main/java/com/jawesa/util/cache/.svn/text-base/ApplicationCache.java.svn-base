package com.jawesa.util.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jawesa.controller.common.ApplicationController;
import com.jawesa.controller.tag.TagController;
import com.jawesa.model.tag.Library;
import com.jawesa.model.tag.Tag;
import com.jawesa.model.tag.TagDemo;

@Named
@ApplicationScoped
@SuppressWarnings("serial")
public class ApplicationCache implements Serializable {
	private Map<Long, Library> libraries = new ConcurrentHashMap<Long, Library>();
	// Library alt key = library.name.toLowerCase()
	private Map<String, Long> librariesAltKeys = new ConcurrentHashMap<String, Long>();

	private Map<Long, Tag> tags = new ConcurrentHashMap<Long, Tag>();
	// Tag alt key = tag.library.altKey +"-"+ tag.name.toLowerCase()
	private Map<String, Long> tagsAltKeys = new ConcurrentHashMap<String, Long>();

	@Inject
	private ApplicationController applicationController;

	@Inject
	private TagController tagController;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		refreshAll();
	}

	private void refreshAll() {
		Map<Long, Library> libraries = new ConcurrentHashMap<Long, Library>();
		Map<Long, Tag> tags = new ConcurrentHashMap<Long, Tag>();

		Map<String, Long> librariesAltKeys = new ConcurrentHashMap<String, Long>();
		Map<String, Long> tagsAltKeys = new ConcurrentHashMap<String, Long>();

		Library library;
		String libraryName;
		String tagName;

		List<Tag> cacheTags = applicationController.initTagCache();
		for (Tag tag : cacheTags) {
			library = tag.getLibrary();
			libraryName = library.getName();

			tagName = tag.getName();
			tags.put(tag.getId(), tag);
			tagsAltKeys.put(Tag.getAltKey(libraryName, tagName), tag.getId());

			libraries.put(library.getId(), library);
			librariesAltKeys.put(Library.getAltKey(libraryName),
					library.getId());
		}

		this.tags = tags;
		this.libraries = libraries;

		this.tagsAltKeys = tagsAltKeys;
		this.librariesAltKeys = librariesAltKeys;
	}

	public void refreshLibraries() {

	}

	public void refreshTags() {
		Map<Long, Tag> tags = new ConcurrentHashMap<Long, Tag>();

		for (Tag tag : tagController.getAllFullTags()) {
			tags.put(tag.getId(), tag);
		}

		this.tags = tags;
	}

	public void refreshTagDemos() {

	}

	public void refreshLibrary(Long id) {

	}

	public void refreshTag(Long id) {
		Tag tag = tagController.getFullTag(id);
		tags.put(id, tag);
	}

	public void refreshTagDemo(Long id) {

	}
	
	public List<Library> getListLibraries() {
		return new ArrayList<Library>(libraries.values());
	}
	
	public TagDemo getTagDemo(Tag tag, String demoSlug) {
		for(TagDemo demo : tag.getDemos()) {
			if(demo.getSlug().equalsIgnoreCase(demoSlug)) {
				return demo;
			}
		}
		
		return null;
	}
	
	public TagDemo getTagDemo(Long idTag, String demoSlug) {
		return getTagDemo(tags.get(idTag), demoSlug);
	}
	
	public TagDemo getTagDemo(String libraryName, String tagName, String demoSlug) {
		Long idTag = tagsAltKeys.get(Tag.getAltKey(libraryName, tagName));
		return getTagDemo(idTag, demoSlug);
	}
	
	public List<TagDemo> getTagDemos(Tag tag) {
		return new ArrayList<TagDemo>(tag.getDemos());
	}
	
	public List<TagDemo> getTagDemos(Long idTag) {
		return getTagDemos(tags.get(idTag));
	}
	
	public List<TagDemo> getTagDemos(String libraryName, String tagName) {
		Long idTag = tagsAltKeys.get(Tag.getAltKey(libraryName, tagName));
		return getTagDemos(idTag);
	}

	public Map<Long, Library> getLibraries() {
		return libraries;
	}

	public Map<Long, Tag> getTags() {
		return tags;
	}

	public Map<String, Long> getLibrariesAltKeys() {
		return librariesAltKeys;
	}

	public Map<String, Long> getTagsAltKeys() {
		return tagsAltKeys;
	}
}
