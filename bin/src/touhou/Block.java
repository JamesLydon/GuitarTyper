package touhou;

public class Block {
	int pos;
	char letter;
	boolean disabled;
	boolean display;
	
	public Block(int pos, char letter)
	{
		this.pos = pos;
		this.letter = letter;
		this.disabled = false;
		this.display = true;
	}
}
