package fr.lordkadoc.biome;

import java.util.Random;

import fr.lordkadoc.element.Element;
import fr.lordkadoc.element.ElementID;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;

public class Desert extends Biome{

	Desert(long seed) {
		super(seed);
	}

	@Override
	public void generer(Chunk chunk) {

		this.random=new Random(seed+chunk.getX()*1000+chunk.getY()*1000000);
		
		chunk.charger();
		
		genererSol(chunk);
		genererElements(chunk);
	}
	
	public void genererSol(Chunk chunk) {
		
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		int biomeD = GenerateurBiome.getSol(new Random(seed + (chunk.getX()+1) *1000  +  chunk.getY()     *1000000).nextInt(GenerateurBiome.length()));
		int biomeG = GenerateurBiome.getSol(new Random(seed + (chunk.getX()-1) *1000  +  chunk.getY()     *1000000).nextInt(GenerateurBiome.length()));
		int biomeH = GenerateurBiome.getSol(new Random(seed +  chunk.getX()    *1000  + (chunk.getY()-1)  *1000000).nextInt(GenerateurBiome.length()));
		int biomeB = GenerateurBiome.getSol(new Random(seed +  chunk.getX()    *1000  + (chunk.getY()+1)  *1000000).nextInt(GenerateurBiome.length()));
		
		for(int i=0;i<cellules.length;i++){
			for(int j=0;j<cellules[i].length;j++){
				cellule = cellules[i][j];
				cellule.setSol_id(2);
				if(i <= 1 && random.nextInt(3) == 0){
					cellule.setSol_id(biomeG);
				}else if(j <= 1 && random.nextInt(3) == 0){
					cellule.setSol_id(biomeH);
				}else if(i >= Chunk.CHUNK_SIZE-2 && random.nextInt(3) == 0){
					cellule.setSol_id(biomeD);
				}else if(j >= Chunk.CHUNK_SIZE-2 && random.nextInt(3) == 0){
					cellule.setSol_id(biomeB);
				}
				
			}
		}
	}
	
	public void genererElements(Chunk chunk){
		
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		for(int i=2;i<cellules.length-2;i++){
			for(int j=2;j<cellules[i].length-2;j++){
				cellule = cellules[i][j];
				if(random.nextInt(100) < 1){
					cellule.ajouterElement(new Element(ElementID.CACTUS_ID));
				}
				
			}
		}
	}

}

