package kz.epam.music;

import java.util.ArrayList;

public class Tracklist {
    private ArrayList<Track> tracklist = new ArrayList<>();


    public ArrayList<Track> getTracklist() {
        return tracklist;
    }

    public void add(ArrayList<Track> tracklist) {
        this.tracklist = tracklist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracklist tracklist1 = (Tracklist) o;

        return tracklist != null ? tracklist.equals(tracklist1.tracklist) : tracklist1.tracklist == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
