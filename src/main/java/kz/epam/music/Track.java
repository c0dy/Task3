package kz.epam.music;


public abstract class Track {
    private String name;
    private Double duration;
    private String style;

    public Track() {}

    public Track(String name, Double duration, String style) {
        this.name = name;
        this.duration = duration;
        this.style = style;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public Double getDuration() {
        return duration;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (duration != null ? !duration.equals(track.duration) : track.duration != null) return false;
        return style != null ? style.equals(track.style) : track.style == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Track:" + name +
                ", duration: " + duration +
                ", style: " + style;
    }
}
