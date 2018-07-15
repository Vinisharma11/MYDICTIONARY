package com.example.user.mydictionary;

public class WordModel {
    private String id,word,wordDef;

    public WordModel(String id, String word, String wordDef) {
        this.id = id;
        this.word = word;
        this.wordDef = wordDef;
    }

    public WordModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordDef() {
        return wordDef;
    }

    public void setWordDef(String wordDef) {
        this.wordDef = wordDef;
    }
}
