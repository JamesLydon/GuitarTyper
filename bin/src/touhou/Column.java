package touhou;

import java.util.ArrayList;

public class Column {

	int columnId;
	int offset;
	char[] letters;
	Block[] blocks;
	int rate;
	int currentRate;
	int lettersIndex;
	
	public Column(int id , int offset , char[] letters, int rate)
	{
		
		Block[] blocks = { 
				new Block(0 - offset,letters[0]),
                new Block(-250 - offset ,letters[1]),
                new Block(-500 - offset ,letters[2]),
                new Block(-750 - offset ,letters[3]),
                new Block(-1000 - offset ,letters[4]),
                new Block(-1250 - offset ,letters[0]),
                new Block(-1500 - offset ,letters[1])
				};
		this.offset = offset;
		this.letters = letters;
		this.blocks = blocks;
		this.rate = rate;
		this.currentRate = 0;
		this.lettersIndex = 2;
		this.columnId = id;
	}
}
