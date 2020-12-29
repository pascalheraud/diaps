package io.github.pascalheraud.diaps.apis;

import java.util.List;
import java.util.Map;

import io.github.pascalheraud.diaps.db.Bilan;
import io.github.pascalheraud.diaps.db.BilanItemReport;
import io.github.pascalheraud.diaps.db.Item;
import io.github.pascalheraud.diaps.db.Personne;

public class BilanReport {
	public Bilan bilan;

	public Personne personne;

	public boolean hasFM;
	public Map<Item.Category, List<BilanItemReport>> formeItems;
	public Map<Item.Category, List<BilanItemReport>> mouvementItems;

	public boolean hasDys;
	public Map<Item.DysGroup, List<BilanItemReport>> dysItems;

	public Integer getWritingSpeedMax() {
		return bilan.writingSpeedMax;
	}
}
