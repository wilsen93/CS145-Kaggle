import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.Arrays;


class FoodType{
	//Variables used to record list of ingredients, amount of each ingredient
	//Number of types of ingredients, and amount of recipes belonging to the class
	String[] ingredients = new String [10000];
	int[] ingredientsnum = new int [10000];
	int incount = 0;
	int recipecount = 0;
	
	//Check if the ingredient is already inside the class
	public Boolean ingredientIn(String a)
	{
		if(Arrays.asList(ingredients).contains(a))
			return true;
		else
			return false;
	}
	
	//Add ingredient to the class
	public void addIngredients(String a)
	{
		if(!ingredientIn(a))
			{
				ingredients[incount] = a;
				ingredientsnum[incount] = 1;
				incount++;
			}
		else
			{
				int index = Arrays.asList(ingredients).indexOf(a);
				ingredientsnum[index] = ingredientsnum[index] + 1;
			}
	}
	
    //Count the probability an ingredient is in a recipe inside the class
    public double ingredientRatio(String a)
	{
		int index = Arrays.asList(ingredients).indexOf(a);
		double value = ingredientsnum[index];
		return value/recipecount;
	}
	
    //Add number of recipe by one
	public void addRecipe()
	{
		recipecount++;
	}
}

public class Cook {
	public static void main(String args[]) throws IOException
	   {	
			//Create a class for each food type
			FoodType greek = new FoodType();
			FoodType southern_us = new FoodType();
			FoodType filipino = new FoodType();
			FoodType indian = new FoodType();
			FoodType jamaican = new FoodType();
			FoodType spanish = new FoodType();
			FoodType italian = new FoodType();
			FoodType mexican = new FoodType();
			FoodType chinese = new FoodType();
			FoodType british = new FoodType();
			FoodType thai = new FoodType();
			FoodType vietnamese = new FoodType();
			FoodType cajun_creole = new FoodType();
			FoodType brazilian = new FoodType();
			FoodType french = new FoodType();
			FoodType japanese = new FoodType();
			FoodType irish = new FoodType();
			FoodType korean = new FoodType();
			FoodType moroccan = new FoodType();
			FoodType russian = new FoodType();
			
			
			String[] ingtype = new String [10000];
			int[] ingamount = new int [10000];
			int count = 0;
			double totalrecipe = 0.0;
			int[] recipeamount = new int [20];
			for(int i = 0; i < 20; i++)
				recipeamount[i] = 0;
			
			//Open input and output files
			File file = new File("./src/train.json");
			BufferedReader f = new BufferedReader(new FileReader(file));
			File file2 = new File("./src/test.json");
			BufferedReader f2 = new BufferedReader(new FileReader(file2));
			FileWriter writer = new FileWriter("./src/submit.csv");
			
			String word = f.readLine();
			String type = "";
			String ingredient = "";

			//Start reading from training dataset
			while(word != null)
			{
				//Find out the type of cuisine and increment the number of recipe
				if(word.contains("cuisine"))
				{	
					type = word.substring(16, word.length()-2);
					totalrecipe = totalrecipe + 1.0;
					if (type.equals("greek"))
					{
						recipeamount[0]++;
						greek.addRecipe();
					}
					else if (type.equals("southern_us"))
					{
						recipeamount[1]++;
						southern_us.addRecipe();
					}
					else if (type.equals("filipino"))
					{
						recipeamount[2]++;
						filipino.addRecipe();
					}
					else if (type.equals("indian"))
					{
						recipeamount[3]++;
						indian.addRecipe();
					}
					else if (type.equals("jamaican"))
					{
						recipeamount[4]++;
						jamaican.addRecipe();
					}
					else if (type.equals("spanish"))
					{
						recipeamount[5]++;
						spanish.addRecipe();
					}
					else if (type.equals("italian"))
					{
						recipeamount[6]++;
						italian.addRecipe();
					}
					else if (type.equals("mexican"))
					{
						recipeamount[7]++;
						mexican.addRecipe();
					}
					else if (type.equals("chinese"))
					{
						recipeamount[8]++;
						chinese.addRecipe();
					}
					else if (type.equals("british"))
					{
						recipeamount[9]++;
						british.addRecipe();
					}
					else if (type.equals("thai"))
					{
						recipeamount[10]++;
						thai.addRecipe();
					}
					else if (type.equals("vietnamese"))
					{
						recipeamount[11]++;
						vietnamese.addRecipe();
					}
					else if (type.equals("cajun_creole"))
					{
						recipeamount[12]++;
						cajun_creole.addRecipe();
					}
					else if (type.equals("brazilian"))
					{
						recipeamount[13]++;
						brazilian.addRecipe();
					}
					else if (type.equals("french"))
					{
						recipeamount[14]++;
						french.addRecipe();
					}
					else if (type.equals("japanese"))
					{
						recipeamount[15]++;
						japanese.addRecipe();
					}
					else if (type.equals("irish"))
					{
						recipeamount[16]++;
						irish.addRecipe();
					}
					else if (type.equals("korean"))
					{
						recipeamount[17]++;
						korean.addRecipe();
					}
					else if (type.equals("moroccan"))
					{
						recipeamount[18]++;
						moroccan.addRecipe();
					}
					else if (type.equals("russian"))
					{
						recipeamount[19]++;
						russian.addRecipe();
					}
				}
				//Find the list of ingredients for the recipe
				if(word.contains("ingredients"))
				{
					word = f.readLine();
					while(!word.contains("]"))
					{
						ingredient = word.substring(7, word.length()-1);
						if (ingredient.endsWith("\""))
						{
							ingredient = ingredient.substring(0, ingredient.length()-1);
						}
						
						//record the number of time the ingredient appears
						if(!Arrays.asList(ingtype).contains(ingredient))
						{
							ingtype[count] = ingredient;
							ingamount[count] = 1;
							count++;
						}
						else
						{
							int index = Arrays.asList(ingtype).indexOf(ingredient);
							ingamount[index] = ingamount[index] + 1;
						}
						
						//add the ingredients to the appropriate class
						switch (type)
						{
							case "greek": greek.addIngredients(ingredient);
											break;
							case "southern_us": southern_us.addIngredients(ingredient);
											break;
							case "filipino": filipino.addIngredients(ingredient);
											break;
							case "indian": indian.addIngredients(ingredient);
											break;
							case "jamaican": jamaican.addIngredients(ingredient);
											break;
							case "spanish": spanish.addIngredients(ingredient);
											break;
							case "italian": italian.addIngredients(ingredient);
											break;
							case "mexican": mexican.addIngredients(ingredient);
											break;
							case "chinese": chinese.addIngredients(ingredient);
											break;
							case "british": british.addIngredients(ingredient);
											break;
							case "thai": thai.addIngredients(ingredient);
											break;
							case "vietnamese": vietnamese.addIngredients(ingredient);
											break;
							case "cajun_creole": cajun_creole.addIngredients(ingredient);
											break;
							case "brazilian": brazilian.addIngredients(ingredient);
											break;
							case "french": french.addIngredients(ingredient);
											break;
							case "japanese": japanese.addIngredients(ingredient);
											break;
							case "irish": irish.addIngredients(ingredient);
											break;
							case "korean": korean.addIngredients(ingredient);
											break;
							case "moroccan": moroccan.addIngredients(ingredient);
											break;
							case "russian": russian.addIngredients(ingredient);
											break;
							default: break;
						}
						word = f.readLine();
					}
				}
				word = f.readLine();
			}
			f.close();
			
			writer.append("id");
			writer.append(',');
			writer.append("cuisine");
			writer.append('\n');
			
			String id = "";
			double[] vote = new double[20];
			for(int i = 0 ; i < 20 ; i++)
			{
				vote[i] = recipeamount[i]/totalrecipe;
			}
			String input = f2.readLine();
			//start reading test dataset
			while(input!=null)	
			{
				//record the recipe id number
				if(input.contains("id"))
				{
					id = input.substring(10, input.length()-1);
				}
				//find the list of ingredients for the recipe
				if(input.contains("ingredients"))
				{
					input = f2.readLine();
					while(!input.contains("]"))
					{
						ingredient = input.substring(7, input.length()-1);
						if (ingredient.endsWith("\""))
						{
							ingredient = ingredient.substring(0, ingredient.length()-1);
						}
						
						//re-calculate the vote based on the current ingredient
						if(greek.ingredientIn(ingredient))
						{
							vote[0]= vote[0] * greek.ingredientRatio(ingredient);
						}
						else
						{
							vote[0]= vote[0] * 0.000001;
						}
						if(southern_us.ingredientIn(ingredient))
						{
							vote[1]= vote[1] * southern_us.ingredientRatio(ingredient);
						}
						else
						{
							vote[1]= vote[1] * 0.000001;
						}
						if(filipino.ingredientIn(ingredient))
						{
							vote[2]= vote[2] * filipino.ingredientRatio(ingredient);
						}
						else
						{
							vote[2]= vote[2] * 0.000001;
						}
						if(indian.ingredientIn(ingredient))
						{
							vote[3]= vote[3] * indian.ingredientRatio(ingredient);
						}
						else
						{
							vote[3]= vote[3] * 0.000001;
						}
						if(jamaican.ingredientIn(ingredient))
						{
							vote[4]= vote[4] * jamaican.ingredientRatio(ingredient);
						}
						else
						{
							vote[4]= vote[4] * 0.000001;
						}
						if(spanish.ingredientIn(ingredient))
						{
							vote[5]= vote[5] * spanish.ingredientRatio(ingredient);
						}
						else
						{
							vote[5]= vote[5] * 0.000001;
						}
						if(italian.ingredientIn(ingredient))
						{
							vote[6]= vote[6] * italian.ingredientRatio(ingredient);
						}
						else
						{
							vote[6]= vote[6] * 0.000001;
						}
						if(mexican.ingredientIn(ingredient))
						{
							vote[7]= vote[7] * mexican.ingredientRatio(ingredient);
						}
						else
						{
							vote[7]= vote[7] * 0.000001;
						}
						if(chinese.ingredientIn(ingredient))
						{
							vote[8]= vote[8] * chinese.ingredientRatio(ingredient);
						}
						else
						{
							vote[8]= vote[8] * 0.000001;
						}
						if(british.ingredientIn(ingredient))
						{
							vote[9]= vote[9] * british.ingredientRatio(ingredient);
						}
						else
						{
							vote[9]= vote[9] * 0.000001;
						}
						if(thai.ingredientIn(ingredient))
						{
							vote[10]= vote[10] * thai.ingredientRatio(ingredient);
						}
						else
						{
							vote[10]= vote[10] * 0.000001;
						}
						if(vietnamese.ingredientIn(ingredient))
						{
							vote[11]= vote[11] * vietnamese.ingredientRatio(ingredient);
						}
						else
						{
							vote[11]= vote[11] * 0.000001;
						}
						if(cajun_creole.ingredientIn(ingredient))
						{
							vote[12]= vote[12] * cajun_creole.ingredientRatio(ingredient);
						}
						else
						{
							vote[12]= vote[12] * 0.000001;
						}
						if(brazilian.ingredientIn(ingredient))
						{
							vote[13]= vote[13] * brazilian.ingredientRatio(ingredient);
						}
						else
						{
							vote[13]= vote[13] * 0.000001;
						}
						if(french.ingredientIn(ingredient))
						{
							vote[14]= vote[14] * french.ingredientRatio(ingredient);
						}
						else
						{
							vote[14]= vote[14] * 0.000001;
						}
						if(japanese.ingredientIn(ingredient))
						{
							vote[15]= vote[15] * japanese.ingredientRatio(ingredient);
						}
						else
						{
							vote[15]= vote[15] * 0.000001;
						}
						if(irish.ingredientIn(ingredient))
						{
							vote[16]= vote[16] * irish.ingredientRatio(ingredient);
						}
						else
						{
							vote[16]= vote[16] * 0.000001;
						}
						if(korean.ingredientIn(ingredient))
						{
							vote[17]= vote[17] * korean.ingredientRatio(ingredient);
						}
						else
						{
							vote[17]= vote[17] * 0.000001;
						}
						if(moroccan.ingredientIn(ingredient))
						{
							vote[18]= vote[18] * moroccan.ingredientRatio(ingredient);
						}
						else
						{
							vote[18]= vote[18] * 0.000001;
						}
						if(russian.ingredientIn(ingredient))
						{
							vote[19]= vote[19] * russian.ingredientRatio(ingredient);
						}
						else
						{
							vote[19]= vote[19] * 0.000001;
						}
					
						input = f2.readLine();
					}
					
					//choose the class with the highest vote and output it
					if (input.contains("]"))
					{
						double max = 0.0;
						
						for(int i = 0; i < 20; i++)
						{
							max = Math.max(max, vote[i]);
						}
						for(int i = 0; i < 20; i++)
						{
							if(vote[i] == max)
							{
								writer.append(id);
								writer.append(',');
								switch(i)
								{
									case 0 : writer.append("greek");
										break;
									case 1 : writer.append("southern_us");
										break;
									case 2 : writer.append("filipino");
										break;
									case 3 : writer.append("indian");
										break;
									case 4 : writer.append("jamaican");
										break;
									case 5 : writer.append("spanish");
										break;
									case 6 : writer.append("italian");
										break;
									case 7 : writer.append("mexican"); 
										break;
									case 8 : writer.append("chinese");
										break;
									case 9 : writer.append("british");
										break;
									case 10 : writer.append("thai");
										break;
									case 11 : writer.append("vietnamese");
									 	break;
									case 12 : writer.append("cajun_creole");
										break;
									case 13 : writer.append("brazilian");
										break;
									case 14 : writer.append("french");
										break;
									case 15 : writer.append("japanese");		  
										break;
									case 16 : writer.append("irish");
										break;
									case 17 : writer.append("korean");
										break;
									case 18 : writer.append("moroccan");
										break;
									case 19 : writer.append("russian");
										break;
									default : break;
								}
								writer.append('\n');
								break;
							}
						}
						for(int i = 0; i < 20; i++)
						{
							vote[i] = recipeamount[i]/totalrecipe;
						}
					}
				}
				input = f2.readLine();
			}
			writer.close();
			f2.close();
		}
}