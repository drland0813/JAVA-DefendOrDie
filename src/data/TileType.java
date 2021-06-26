
package data;

public enum TileType {
	Space0("Ally_block_64x64", false), Way("enemies_block_64x64", false), Crystal("Crystal", false), 
	
	TTopL("Top_left", false), Top("Top", false),  TopR("Top_right", false),
	
	T8("Left_side", false), T9("Heroes_block_64x64", true),  T10("Right_side", false),
	
	T11("Bottom_Leftt", false), T12("Bottom", false), T13("Bottom_right", false), T4("blank_block", false),
	
	T23("Sprite-0023", false), T2327("Sprite-0024", false),T27("Sprite-0027", false),TSpot("4_New_Enemy_Spawn_Spot",false),THouse("3_House",false), 
	NULL("g11", false), 
	;

	
	
	String textureName;
	boolean buildable;

	TileType(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
