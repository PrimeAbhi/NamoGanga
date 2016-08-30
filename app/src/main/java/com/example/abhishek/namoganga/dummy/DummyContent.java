package com.example.abhishek.namoganga.dummy;

import java.util.ArrayList;
import java.util.List;

import com.example.abhishek.namoganga.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();
    public static final List<DummyItem> Gallery_ITEMS = new ArrayList<>();
    private static String[] places_names = {"Har Ki Pauri", "Mansa Devi Temple", "Bharat Mata Mandir", "Chandi Devi Temple",
            "Vaishno Devi Temple", "Daksheswara Mahadev Temple", "Shantikunj", "Gau Ghat",
            "Kushavarta Ghat", "Vishnu Ghat", "Asthi Pravah Ghat", "Subhash Ghat", "Gurukula Kangri Vishwavidyalaya",
            "Faculty of Engineering & Technology"};


    private static int[] image_list = {R.drawable.har_ki_pauri, R.drawable.mansa_devi, R.drawable.bharat_mata_mandir, R.drawable.chandi_devi,
            R.drawable.vaishno_devi, R.drawable.daksha_mandir, R.drawable.shanti_kunj, R.drawable.gau_ghat, R.drawable.kush_ghat, R.drawable.vishnu_ghat,
            R.drawable.ashthi_pravah, R.drawable.subhash_ghat, R.drawable.gkv, R.drawable.fet_gkv};

    private static int[] gallery_list = {R.drawable.gallery, R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3, R.drawable.gallery4,
            R.drawable.gallery5, R.drawable.gallery6, R.drawable.gallery7, R.drawable.gallery8, R.drawable.gallery9,
            R.drawable.gallery10, R.drawable.gallery11, R.drawable.gallery12, R.drawable.article4};

    private static final int COUNT = 14;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createDummyItem(i));
            addItemForGallery(createGalleryItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
    }

    private static void addItemForGallery(DummyItem item) {
        Gallery_ITEMS.add(item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(image_list[position], places_names[position]);
    }

    private static DummyItem createGalleryItem(int position) {
        return new DummyItem(gallery_list[position]);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final int id;
        public final String content;

        public DummyItem(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public DummyItem(int id) {
            this.id = id;
            content = null;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
