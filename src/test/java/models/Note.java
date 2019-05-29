package models;


public class Note {



    private String title;



    private String author;
    private String message;

    public Note(String title, String author, String message) {
        this.title = title;
        this.author = author;
        this.message = message;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getMessage(){
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args){
        Note note = new Note("titulok 1", "zdena", "sprava 1");
        System.out.println(note.getTitle());
        note.setTitle("titulok 2");
        System.out.println(note.getTitle());
        note.setAuthor("bozidara");
        System.out.println(note.getMessage());
        System.out.println(note.getAuthor());
    }
}
