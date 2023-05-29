package entity;

public class ChatMessage {

    private String message;
    private ChatUser author;
    private long timestamp;
    private long sec = 60; // было 30 секунд это задание 4
    private long quantity = 15; // это задание 3
    private String HTMLtegs ="<a>,<p>,<body>,<html>,<head>,<div>";
    String[] ErrorHTML = HTMLtegs.split(",");


    public ChatMessage(String message, ChatUser author, long timestamp) {
        message = HTMLFilter(message);
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }
    private String HTMLFilter(String message)
    {
        for (int i =0;i< ErrorHTML.length;i++)
        {
            message=message.replace(ErrorHTML[i],"");
        }
        return message;
    }
    public long getQuantity()
    {return quantity;}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatUser getAuthor() {
        return author;
    }

    public void setAuthor(ChatUser author) {
        this.author = author;
    }

    public long getSec()
    {
        return sec;
    }

}
