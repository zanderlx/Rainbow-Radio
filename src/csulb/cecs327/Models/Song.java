package csulb.cecs327.Models;

public class Song {
    private double modeConfidence;
    private double hotttnesss;
    private int year;
    private double beatsConfidence;
    private int artistMbtagsCount;
    private double barsStart;
    private double loudness;
    private double tempo;
    private double endOfFadeIn;
    private String title;
    private double keyConfidence;
    private double duration;
    private double beatsStart;
    private double barsConfidence;
    private int mode;
    private String artistMbtags;
    private double startOfFadeOut;
    private double timeSignatureConfidence;
    private String id;
    private double tatumsConfidence;
    private double key;
    private double tatumsStart;
    private int timeSignature;

    public void setModeConfidence(double modeConfidence) {
        this.modeConfidence = modeConfidence;
    }

    public double getModeConfidence() {
        return modeConfidence;
    }

    public void setHotttnesss(double hotttnesss) {
        this.hotttnesss = hotttnesss;
    }

    public double getHotttnesss() {
        return hotttnesss;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setBeatsConfidence(double beatsConfidence) {
        this.beatsConfidence = beatsConfidence;
    }

    public double getBeatsConfidence() {
        return beatsConfidence;
    }

    public void setArtistMbtagsCount(int artistMbtagsCount) {
        this.artistMbtagsCount = artistMbtagsCount;
    }

    public int getArtistMbtagsCount() {
        return artistMbtagsCount;
    }

    public void setBarsStart(double barsStart) {
        this.barsStart = barsStart;
    }

    public double getBarsStart() {
        return barsStart;
    }

    public void setLoudness(double loudness) {
        this.loudness = loudness;
    }

    public double getLoudness() {
        return loudness;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public double getTempo() {
        return tempo;
    }

    public void setEndOfFadeIn(double endOfFadeIn) {
        this.endOfFadeIn = endOfFadeIn;
    }

    public double getEndOfFadeIn() {
        return endOfFadeIn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setKeyConfidence(double keyConfidence) {
        this.keyConfidence = keyConfidence;
    }

    public double getKeyConfidence() {
        return keyConfidence;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public void setBeatsStart(double beatsStart) {
        this.beatsStart = beatsStart;
    }

    public double getBeatsStart() {
        return beatsStart;
    }

    public void setBarsConfidence(double barsConfidence) {
        this.barsConfidence = barsConfidence;
    }

    public double getBarsConfidence() {
        return barsConfidence;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setArtistMbtags(String artistMbtags) {
        this.artistMbtags = artistMbtags;
    }

    public String getArtistMbtags() {
        return artistMbtags;
    }

    public void setStartOfFadeOut(double startOfFadeOut) {
        this.startOfFadeOut = startOfFadeOut;
    }

    public double getStartOfFadeOut() {
        return startOfFadeOut;
    }

    public void setTimeSignatureConfidence(double timeSignatureConfidence) {
        this.timeSignatureConfidence = timeSignatureConfidence;
    }

    public double getTimeSignatureConfidence() {
        return timeSignatureConfidence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTatumsConfidence(double tatumsConfidence) {
        this.tatumsConfidence = tatumsConfidence;
    }

    public double getTatumsConfidence() {
        return tatumsConfidence;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getKey() {
        return key;
    }

    public void setTatumsStart(double tatumsStart) {
        this.tatumsStart = tatumsStart;
    }

    public double getTatumsStart() {
        return tatumsStart;
    }

    public void setTimeSignature(int timeSignature) {
        this.timeSignature = timeSignature;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    @Override
    public String toString() {
        return
                "Song{" +
                        "mode_confidence = '" + modeConfidence + '\'' +
                        ",hotttnesss = '" + hotttnesss + '\'' +
                        ",year = '" + year + '\'' +
                        ",beats_confidence = '" + beatsConfidence + '\'' +
                        ",artist_mbtags_count = '" + artistMbtagsCount + '\'' +
                        ",bars_start = '" + barsStart + '\'' +
                        ",loudness = '" + loudness + '\'' +
                        ",tempo = '" + tempo + '\'' +
                        ",end_of_fade_in = '" + endOfFadeIn + '\'' +
                        ",title = '" + title + '\'' +
                        ",key_confidence = '" + keyConfidence + '\'' +
                        ",duration = '" + duration + '\'' +
                        ",beats_start = '" + beatsStart + '\'' +
                        ",bars_confidence = '" + barsConfidence + '\'' +
                        ",mode = '" + mode + '\'' +
                        ",artist_mbtags = '" + artistMbtags + '\'' +
                        ",start_of_fade_out = '" + startOfFadeOut + '\'' +
                        ",time_signature_confidence = '" + timeSignatureConfidence + '\'' +
                        ",id = '" + id + '\'' +
                        ",tatums_confidence = '" + tatumsConfidence + '\'' +
                        ",key = '" + key + '\'' +
                        ",tatums_start = '" + tatumsStart + '\'' +
                        ",time_signature = '" + timeSignature + '\'' +
                        "}";
    }
}

