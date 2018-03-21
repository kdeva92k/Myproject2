package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "episode_type_list")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeTypeList {

	@Id
	@Column(name="episode_type_list_id")
	private Integer episodeTypeListId;

	@Column(name="episode_type_list_name")
	private String episodeTypeListName;

	@Column(name="episode_type_list_typeid")
	private Integer episodeTypeListTypeid;

	public Integer getEpisodeTypeListId() {
		return episodeTypeListId;
	}

	public void setEpisodeTypeListId(Integer episodeTypeListId) {
		this.episodeTypeListId = episodeTypeListId;
	}

	public String getEpisodeTypeListName() {
		return episodeTypeListName;
	}

	public void setEpisodeTypeListName(String episodeTypeListName) {
		this.episodeTypeListName = episodeTypeListName;
	}

	public Integer getEpisodeTypeListTypeid() {
		return episodeTypeListTypeid;
	}

	public void setEpisodeTypeListTypeid(Integer episodeTypeListTypeid) {
		this.episodeTypeListTypeid = episodeTypeListTypeid;
	}
}