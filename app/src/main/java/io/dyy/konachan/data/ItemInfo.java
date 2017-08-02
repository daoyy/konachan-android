package io.dyy.konachan.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dyy on 2017/7/29.
 * @param
 */
public class ItemInfo implements Serializable{
    /**
     * id : 247439
     * tags : game_cg group hanabusa_koharu hatsujou_sprinkle hinata_mio maid meidou_hazuki mikagami_mamizu momosaki_shizuku waitress whirlpool
     * created_at : 1501388478
     * creator_id : 147781
     * author : luckyluna
     * change : 1307527
     * source :
     * score : 14
     * md5 : b8e18fa088d634c51bd4e10d55239bee
     * file_size : 2957964
     * file_url : //konachan.com/image/b8e18fa088d634c51bd4e10d55239bee/Konachan.com%20-%20247439%20game_cg%20group%20hanabusa_koharu%20hatsujou_sprinkle%20hinata_mio%20maid%20meidou_hazuki%20mikagami_mamizu%20momosaki_shizuku%20waitress%20whirlpool.png
     * is_shown_in_index : true
     * preview_url : //konachan.com/data/preview/b8/e1/b8e18fa088d634c51bd4e10d55239bee.jpg
     * preview_width : 150
     * preview_height : 84
     * actual_preview_width : 300
     * actual_preview_height : 169
     * sample_url : //konachan.com/sample/b8e18fa088d634c51bd4e10d55239bee/Konachan.com%20-%20247439%20sample.jpg
     * sample_width : 1500
     * sample_height : 844
     * sample_file_size : 902772
     * jpeg_url : //konachan.com/jpeg/b8e18fa088d634c51bd4e10d55239bee/Konachan.com%20-%20247439%20game_cg%20group%20hanabusa_koharu%20hatsujou_sprinkle%20hinata_mio%20maid%20meidou_hazuki%20mikagami_mamizu%20momosaki_shizuku%20waitress%20whirlpool.jpg
     * jpeg_width : 1920
     * jpeg_height : 1080
     * jpeg_file_size : 695175
     * rating : s
     * has_children : false
     * parent_id : null
     * status : active
     * width : 1920
     * height : 1080
     * is_held : false
     * frames_pending_string :
     * frames_pending : []
     * frames_string :
     * frames : []
     */

    private int id;
    private String tags;
    private int created_at;
    private int creator_id;
    private String author;
    private int change;
    private String source;
    private int score;
    private String md5;
    private int file_size;
    private String file_url;
    private boolean is_shown_in_index;
    private String preview_url;
    private int preview_width;
    private int preview_height;
    private int actual_preview_width;
    private int actual_preview_height;
    private String sample_url;
    private int sample_width;
    private int sample_height;
    private int sample_file_size;
    private String jpeg_url;
    private int jpeg_width;
    private int jpeg_height;
    private int jpeg_file_size;
    private String rating;
    private boolean has_children;
    private Object parent_id;
    private String status;
    private int width;
    private int height;
    private boolean is_held;
    private String frames_pending_string;
    private String frames_string;
    private List<?> frames_pending;
    private List<?> frames;

    /**
     *
     * @param id
     * @param tags
     * @param created_at
     * @param creator_id
     * @param author
     * @param change
     * @param source
     * @param score
     * @param md5
     * @param file_size
     * @param file_url
     * @param is_shown_in_index
     * @param preview_url
     * @param preview_width
     * @param preview_height
     * @param actual_preview_width
     * @param actual_preview_height
     * @param sample_url
     * @param sample_width
     * @param sample_height
     * @param sample_file_size
     * @param jpeg_url
     * @param jpeg_width
     * @param jpeg_height
     * @param jpeg_file_size
     * @param rating
     * @param has_children
     * @param parent_id
     * @param status
     * @param width
     * @param height
     * @param is_held
     * @param frames_pending_string
     * @param frames_string
     * @param frames_pending
     * @param frames
     */
    public ItemInfo(int id, String tags, int created_at, int creator_id, String author, int change, String source, int score, String md5, int file_size, String file_url, boolean is_shown_in_index, String preview_url, int preview_width, int preview_height, int actual_preview_width, int actual_preview_height, String sample_url, int sample_width, int sample_height, int sample_file_size, String jpeg_url, int jpeg_width, int jpeg_height, int jpeg_file_size, String rating, boolean has_children, Object parent_id, String status, int width, int height, boolean is_held, String frames_pending_string, String frames_string, List<?> frames_pending, List<?> frames) {
        this.id = id;
        this.tags = tags;
        this.created_at = created_at;
        this.creator_id = creator_id;
        this.author = author;
        this.change = change;
        this.source = source;
        this.score = score;
        this.md5 = md5;
        this.file_size = file_size;
        this.file_url = file_url;
        this.is_shown_in_index = is_shown_in_index;
        this.preview_url = preview_url;
        this.preview_width = preview_width;
        this.preview_height = preview_height;
        this.actual_preview_width = actual_preview_width;
        this.actual_preview_height = actual_preview_height;
        this.sample_url = sample_url;
        this.sample_width = sample_width;
        this.sample_height = sample_height;
        this.sample_file_size = sample_file_size;
        this.jpeg_url = jpeg_url;
        this.jpeg_width = jpeg_width;
        this.jpeg_height = jpeg_height;
        this.jpeg_file_size = jpeg_file_size;
        this.rating = rating;
        this.has_children = has_children;
        this.parent_id = parent_id;
        this.status = status;
        this.width = width;
        this.height = height;
        this.is_held = is_held;
        this.frames_pending_string = frames_pending_string;
        this.frames_string = frames_string;
        this.frames_pending = frames_pending;
        this.frames = frames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public boolean isIs_shown_in_index() {
        return is_shown_in_index;
    }

    public void setIs_shown_in_index(boolean is_shown_in_index) {
        this.is_shown_in_index = is_shown_in_index;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public int getPreview_width() {
        return preview_width;
    }

    public void setPreview_width(int preview_width) {
        this.preview_width = preview_width;
    }

    public int getPreview_height() {
        return preview_height;
    }

    public void setPreview_height(int preview_height) {
        this.preview_height = preview_height;
    }

    public int getActual_preview_width() {
        return actual_preview_width;
    }

    public void setActual_preview_width(int actual_preview_width) {
        this.actual_preview_width = actual_preview_width;
    }

    public int getActual_preview_height() {
        return actual_preview_height;
    }

    public void setActual_preview_height(int actual_preview_height) {
        this.actual_preview_height = actual_preview_height;
    }

    public String getSample_url() {
        return sample_url;
    }

    public void setSample_url(String sample_url) {
        this.sample_url = sample_url;
    }

    public int getSample_width() {
        return sample_width;
    }

    public void setSample_width(int sample_width) {
        this.sample_width = sample_width;
    }

    public int getSample_height() {
        return sample_height;
    }

    public void setSample_height(int sample_height) {
        this.sample_height = sample_height;
    }

    public int getSample_file_size() {
        return sample_file_size;
    }

    public void setSample_file_size(int sample_file_size) {
        this.sample_file_size = sample_file_size;
    }

    public String getJpeg_url() {
        return jpeg_url;
    }

    public void setJpeg_url(String jpeg_url) {
        this.jpeg_url = jpeg_url;
    }

    public int getJpeg_width() {
        return jpeg_width;
    }

    public void setJpeg_width(int jpeg_width) {
        this.jpeg_width = jpeg_width;
    }

    public int getJpeg_height() {
        return jpeg_height;
    }

    public void setJpeg_height(int jpeg_height) {
        this.jpeg_height = jpeg_height;
    }

    public int getJpeg_file_size() {
        return jpeg_file_size;
    }

    public void setJpeg_file_size(int jpeg_file_size) {
        this.jpeg_file_size = jpeg_file_size;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isHas_children() {
        return has_children;
    }

    public void setHas_children(boolean has_children) {
        this.has_children = has_children;
    }

    public Object getParent_id() {
        return parent_id;
    }

    public void setParent_id(Object parent_id) {
        this.parent_id = parent_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIs_held() {
        return is_held;
    }

    public void setIs_held(boolean is_held) {
        this.is_held = is_held;
    }

    public String getFrames_pending_string() {
        return frames_pending_string;
    }

    public void setFrames_pending_string(String frames_pending_string) {
        this.frames_pending_string = frames_pending_string;
    }

    public String getFrames_string() {
        return frames_string;
    }

    public void setFrames_string(String frames_string) {
        this.frames_string = frames_string;
    }

    public List<?> getFrames_pending() {
        return frames_pending;
    }

    public void setFrames_pending(List<?> frames_pending) {
        this.frames_pending = frames_pending;
    }

    public List<?> getFrames() {
        return frames;
    }

    public void setFrames(List<?> frames) {
        this.frames = frames;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "id=" + id +
                ", tags='" + tags + '\'' +
                ", created_at=" + created_at +
                ", creator_id=" + creator_id +
                ", author='" + author + '\'' +
                ", change=" + change +
                ", source='" + source + '\'' +
                ", score=" + score +
                ", md5='" + md5 + '\'' +
                ", file_size=" + file_size +
                ", file_url='" + file_url + '\'' +
                ", is_shown_in_index=" + is_shown_in_index +
                ", preview_url='" + preview_url + '\'' +
                ", preview_width=" + preview_width +
                ", preview_height=" + preview_height +
                ", actual_preview_width=" + actual_preview_width +
                ", actual_preview_height=" + actual_preview_height +
                ", sample_url='" + sample_url + '\'' +
                ", sample_width=" + sample_width +
                ", sample_height=" + sample_height +
                ", sample_file_size=" + sample_file_size +
                ", jpeg_url='" + jpeg_url + '\'' +
                ", jpeg_width=" + jpeg_width +
                ", jpeg_height=" + jpeg_height +
                ", jpeg_file_size=" + jpeg_file_size +
                ", rating='" + rating + '\'' +
                ", has_children=" + has_children +
                ", parent_id=" + parent_id +
                ", status='" + status + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", is_held=" + is_held +
                ", frames_pending_string='" + frames_pending_string + '\'' +
                ", frames_string='" + frames_string + '\'' +
                ", frames_pending=" + frames_pending +
                ", frames=" + frames +
                '}';
    }
}
