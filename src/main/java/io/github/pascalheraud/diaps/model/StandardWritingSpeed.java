package io.github.pascalheraud.diaps.model;

public class StandardWritingSpeed {
	public Integer girlNormal;
	public Integer boyNormal;
	public Integer girlMaximal;
	public Integer boyMaximal;

	public StandardWritingSpeed(Integer boyNormal, Integer girlNormal, Integer boyMaximal, Integer girlMaximal) {
		this.girlNormal = girlNormal;
		this.boyNormal = boyNormal;
		this.girlMaximal = girlMaximal;
		this.boyMaximal = boyMaximal;
	}
}
