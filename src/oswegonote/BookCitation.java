package oswegonote;

public class BookCitation extends Citation{
    String dateOfPublication = ""; //just ask for year
    String publisher = "";
    String wherePublisher = "";
    
    public String getDateOfPublication(){
        return dateOfPublication;
    }//end getter method
    
    public void setDateOfPublication(String dateOfPublication){
        this.dateOfPublication = dateOfPublication;
    }//end setter method
    
    public String getPublisher(){
        return publisher;
    }//end getter method
    
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }//end setter method
    
    public String getWherePublisher(){
        return wherePublisher;
    }//end getter method
    
    public void setWherePublisher(String wherePublisher){
        this.wherePublisher = wherePublisher;
    }//end setter method
    
    public BookCitation(String name){
        super(name);
    }//end BookCitation constructor method
    
    @Override
    public String formatIEEE(){
        String citationOutput = "";
        String authorsOutput = "";
        String name = getName();
        String dateOfPublication = getDateOfPublication();
        String publisher = getPublisher();
        String wherePublisher = getWherePublisher();

        //The author array is created to be the size of the maxNumber of authors
        String[] authors = new String[Main.getMaxNumOfAuthors()];

        //internal authorCount
        int authorCount = 0;
        
        //for loop to getAuthors
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(getAuthor(j) != null){
                authors[j] = getAuthor(j);
                authorCount++;
            }//end if statement
        }//end for loop

        //Put authors into IEEE FORMAT                  
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            String fullName = "";
            String[] splitString;
            
            if(authors[j] != null){
                fullName = authors[j];               

                splitString = fullName.split("\\s+");

                for(int i = 0; i < splitString.length; i++){
                    splitString[i] = splitString[i].substring(0,1).toUpperCase() + splitString[i].substring(1).toLowerCase();
                }//end for loop                
                              
                //if the authors last name is not the final name given.
                if(splitString.length == 1){           
                    fullName = splitString[0];
                }//end if statement

                //if two names are provided
                else if(splitString.length == 2){            
                    fullName = splitString[0].substring(0, 1) + ". " + splitString[1];           
                }//end else if statement

                //if three or more names is provided
                else if(splitString.length == 3){                     
                    fullName = splitString[0].substring(0, 1) + ". " + splitString[1].substring(0,1) + ". " + splitString[splitString.length - 1];
                }//end else statement   
            }//end if statement   
            authors[j] = fullName;
        }//end for loop 
                      
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(!authors[j].equals("")){                    
                if(j < authorCount - 2){
                    authorsOutput = authorsOutput + authors[j] + ", ";                    
                }//end if statement              
                
                else if(j == authorCount - 1 && authorCount > 1){
                    authorsOutput = authorsOutput + ", and " + authors[j];
                }
               
                else{
                    authorsOutput = authorsOutput + authors[j];                                   
                }//end else statement                     
            }//end if statement
        }//end for loop

        citationOutput = "[" + getID() + "] " + authorsOutput + ", <i>" + name + "</i>. " + wherePublisher + ": " + publisher + ", " + dateOfPublication + ".<br>";
        return citationOutput;
    }//end formatIEEE()
    
    @Override
    public String formatAPA(){
        String citationOutput = "";
        String authorsOutput = "";
        String name = getName();
        String dateOfPublication = getDateOfPublication();
        String publisher = getPublisher();
        String wherePublisher = getWherePublisher();
                    
        //The author array is created to be the size of the maxNumber of authors
        String[] authors = new String[Main.getMaxNumOfAuthors()];

        //internal authorCount
        int authorCount = 0;
        
        //for loop to getAuthors
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(getAuthor(j) != null){
                authors[j] = getAuthor(j);
                authorCount++;
            }//end if statement
        }//end for loop

        //Put authors into APA FORMAT                  
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            String fullName = "";
            String[] splitString;
            
            if(authors[j] != null){
                fullName = authors[j];               

                splitString = fullName.split("\\s+");

                for(int i = 0; i < splitString.length; i++){
                    splitString[i] = splitString[i].substring(0,1).toUpperCase() + splitString[i].substring(1).toLowerCase();
                }//end for loop
                
                //if the authors last name is the final name given.
                if(splitString.length == 1 && j == authorCount - 1){           
                    fullName = splitString[0] + ".";
                }//end if statement
                
                //if the authors last name is not the final name given.
                else if(splitString.length == 1){           
                    fullName = splitString[0];
                }//end if statement

                //if two names are provided
                else if(splitString.length == 2){            
                    fullName = splitString[1] + ", " + splitString[0].substring(0, 1) + ".";           
                }//end else if statement

                //if three or more names is provided
                else if(splitString.length == 3){                     
                    fullName = splitString[splitString.length - 1] + ", " + splitString[0].substring(0, 1) + ". " + splitString[1].substring(0,1) + ".";
                }//end else statement   
            }//end if statement   
            authors[j] = fullName;
        }//end for loop 
                      
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(!authors[j].equals("")){                    
                if(j < authorCount - 2){
                    authorsOutput = authorsOutput + authors[j] + ", ";                    
                }//end if statement              
                
                else if(j == authorCount - 1 && authorCount > 1){
                    authorsOutput = authorsOutput + ", & " + authors[j];
                }
               
                else{
                    authorsOutput = authorsOutput + authors[j];                                   
                }//end else statement                     
            }//end if statement
        }//end for loop.

        citationOutput = authorsOutput + " (" + dateOfPublication + ")." + " <i>" + name + "</i>. " + wherePublisher + ": " + publisher + ".<br>";
        return citationOutput;        
    }//end formatAPA()
    
    @Override
    public String formatACM(){
        String citationOutput = "";
        String authorsOutput = "";
        String name = getName();
        String dateOfPublication = getDateOfPublication();
        String publisher = getPublisher();
        String wherePublisher = getWherePublisher();
        //This will allow us to return only the city that was input.
        String[] splitWherePublisher = wherePublisher.split(" ");
            
        //The author array is created to be the size of the maxNumber of authors
        String[] authors = new String[Main.getMaxNumOfAuthors()];

        //internal authorCount
        int authorCount = 0;
        
        //for loop to getAuthors
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(getAuthor(j) != null){
                authors[j] = getAuthor(j);
                authorCount++;
            }//end if statement
        }//end for loop

        //Put authors into ACM FORMAT                  
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            String fullName = "";
            String[] splitString;
            
            if(authors[j] != null){
                fullName = authors[j];               

                splitString = fullName.split("\\s+");

                for(int i = 0; i < splitString.length; i++){
                    splitString[i] = splitString[i].substring(0,1).toUpperCase() + splitString[i].substring(1).toLowerCase();
                }//end for loop
                
                //if the authors last name is the final name given.
                if(splitString.length == 1 && j == authorCount - 1){           
                    fullName = splitString[0] + ".";
                }//end if statement
                
                //if the authors last name is not the final name given.
                else if(splitString.length == 1){           
                    fullName = splitString[0];
                }//end if statement

                //if two names are provided
                else if(splitString.length == 2){            
                    fullName = splitString[1] + ", " + splitString[0].substring(0, 1) + ".";           
                }//end else if statement

                //if three or more names is provided
                else if(splitString.length == 3){                     
                    fullName = splitString[splitString.length - 1] + ", " + splitString[0].substring(0, 1) + ". " + splitString[1].substring(0,1) + ".";
                }//end else statement   
            }//end if statement   
            authors[j] = fullName;
        }//end for loop 
                      
        for(int j = 0; j < Main.getMaxNumOfAuthors(); j++){
            if(!authors[j].equals("")){                    
                if(j < authorCount - 2){
                    authorsOutput = authorsOutput + authors[j] + ", ";                    
                }//end if statement              
                
                else if(j == authorCount - 1 && authorCount > 1){
                    authorsOutput = authorsOutput + " and " + authors[j];
                }
               
                else{
                    authorsOutput = authorsOutput + authors[j];                                   
                }//end else statement                     
            }//end if statement
        }//end for loop.

        citationOutput = authorsOutput + " <i>" + name + "</i>. " + publisher + ", " + splitWherePublisher[0] + " " + dateOfPublication + ".<br>";
        return citationOutput;        
    }//end formatACM()
}//end BookCitation class
