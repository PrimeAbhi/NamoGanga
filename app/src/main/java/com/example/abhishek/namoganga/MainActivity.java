package com.example.abhishek.namoganga;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abhishek.namoganga.dummy.DummyContent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemFragment.OnListFragmentInteractionListener {
    String title = "About Ganga";
    long lastPress;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    final Location Loc_HARI_KI_PAURI = new Location("");
    final Location Loc_MANSA_DEVI = new Location("");
    final Location Loc_BHARAT_MATA_MANDIR = new Location("");
    final Location Loc_CHANDI_DEVI_MANDIR = new Location("");
    final Location Loc_VAISHNO_DEVI_MANDIR = new Location("");
    final Location Loc_DAKSHA_MAHADEV_TEMPLE = new Location("");
    final Location Loc_SATI_KHUND = new Location("");
    final Location Loc_SHANTI_KUNJ = new Location("");
    final Location Loc_VIP_GHAT = new Location("");
    final Location Loc_GAU_GHAT = new Location("");
    final Location Loc_KUSHAVARTA_GHAT = new Location("");
    final Location Loc_VISHNU_GHAT = new Location("");
    final Location Loc_BIRLA_GHAT = new Location("");
    final Location Loc_ASTHI_PARVAT_GHAT = new Location("");
    final Location Loc_SHUBHAS_GHAT = new Location("");
    final Location Loc_RAM_GHAT = new Location("");
    final Location Loc_PREM_NAGAR_GHAT = new Location("");
    final Location Loc_SINGH_DWAR_GHAT = new Location("");
    final Location Loc_JATWARA_PUL_GHAT = new Location("");
    final Location Loc_GURUKUL_KANGRI = new Location("");
    final Location Loc_FET = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Create Drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //Setup Drawer View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_first_fragment);
            setFragment(new AboutGanga(), title);
        } else {
            title = savedInstanceState.getString("title");
            setTitle(title);
        }

        //On Header Icon Touch
        View headerView = navigationView.getHeaderView(0);
        ImageView headerImage = (ImageView) headerView.findViewById(R.id.header_image);
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(result)) {
                GooglePlayServicesUtil.getErrorDialog(result, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(), "Location Services does not supported", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            Intent intent = new Intent(this, Notifications.class);
            startService(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastPress > 3000) {
                    Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_LONG).show();
                    lastPress = currentTime;
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        title = item.getTitle().toString();
        if (id == R.id.nav_first_fragment) {
            setFragment(new AboutGanga(), title);
        } else if (id == R.id.nav_second_fragment) {
            setFragment(new ItemFragment(), title);
        } else if (id == R.id.nav_third_fragment) {
            setFragment(new PublicArticles(), title);
        } else if (id == R.id.nav_fourth_fragment) {
            setFragment(new GalleryFragment(), title);
        } else if (id == R.id.nav_fifth_fragment) {
            setFragment(new AbtNMCGFragment(), title);
        } else if (id == R.id.nav_sixth_fragment) {
            setFragment(new AbtAppFragment(), title);
        }
        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(final Fragment fragment, final String title) {
        // Set title
        setTitle(title);
        // Set fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_replace, fragment)
                .commit();
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item, int position) {

        Loc_HARI_KI_PAURI.setLatitude(29.956603);
        Loc_HARI_KI_PAURI.setLongitude(78.171496);
        Loc_MANSA_DEVI.setLatitude(29.957632);
        Loc_MANSA_DEVI.setLongitude(78.165240);
        Loc_BHARAT_MATA_MANDIR.setLatitude(29.984871);
        Loc_BHARAT_MATA_MANDIR.setLongitude(78.191897);
        Loc_CHANDI_DEVI_MANDIR.setLatitude(29.993659);
        Loc_CHANDI_DEVI_MANDIR.setLongitude(78.179867);
        Loc_VAISHNO_DEVI_MANDIR.setLatitude(29.930595);
        Loc_VAISHNO_DEVI_MANDIR.setLongitude(78.119500);
        Loc_DAKSHA_MAHADEV_TEMPLE.setLatitude(29.921800);
        Loc_DAKSHA_MAHADEV_TEMPLE.setLongitude(78.145893);
        Loc_SATI_KHUND.setLatitude(29.920582);
        Loc_SATI_KHUND.setLongitude(78.138275);
        Loc_SHANTI_KUNJ.setLatitude(29.988800);
        Loc_SHANTI_KUNJ.setLongitude(78.191895);

        Loc_VIP_GHAT.setLatitude(29.957087);
        Loc_VIP_GHAT.setLongitude(78.173629);
        Loc_GAU_GHAT.setLatitude(29.954063);
        Loc_GAU_GHAT.setLongitude(78.169415);
        Loc_KUSHAVARTA_GHAT.setLatitude(29.953305);
        Loc_KUSHAVARTA_GHAT.setLongitude(78.168668);
        Loc_VISHNU_GHAT.setLatitude(29.951131);
        Loc_VISHNU_GHAT.setLongitude(78.165816);
        Loc_BIRLA_GHAT.setLatitude(29.947786);
        Loc_BIRLA_GHAT.setLongitude(78.161945);
        Loc_ASTHI_PARVAT_GHAT.setLatitude(29.948310);
        Loc_ASTHI_PARVAT_GHAT.setLongitude(78.162265);
        Loc_SHUBHAS_GHAT.setLatitude(29.954771);
        Loc_SHUBHAS_GHAT.setLongitude(78.170109);
        Loc_RAM_GHAT.setLatitude(29.951718);
        Loc_RAM_GHAT.setLongitude(78.166497);
        Loc_PREM_NAGAR_GHAT.setLatitude(29.930107);
        Loc_PREM_NAGAR_GHAT.setLongitude(78.135989);
        Loc_SINGH_DWAR_GHAT.setLatitude(29.926134);
        Loc_SINGH_DWAR_GHAT.setLongitude(78.131732);
        Loc_JATWARA_PUL_GHAT.setLatitude(29.918943);
        Loc_JATWARA_PUL_GHAT.setLongitude(78.103515);

        Loc_GURUKUL_KANGRI.setLatitude(29.923887);
        Loc_GURUKUL_KANGRI.setLongitude(78.127502);
        Loc_FET.setLatitude(29.916866);
        Loc_FET.setLongitude(78.063784);

        final ArrayList<Location> locList = new ArrayList<Location>();
        locList.add(Loc_HARI_KI_PAURI);
        locList.add(Loc_MANSA_DEVI);
        locList.add(Loc_BHARAT_MATA_MANDIR);
        locList.add(Loc_CHANDI_DEVI_MANDIR);
        locList.add(Loc_VAISHNO_DEVI_MANDIR);
        locList.add(Loc_DAKSHA_MAHADEV_TEMPLE);
        locList.add(Loc_SHANTI_KUNJ);
        locList.add(Loc_GAU_GHAT);
        locList.add(Loc_KUSHAVARTA_GHAT);
        locList.add(Loc_VISHNU_GHAT);
        locList.add(Loc_ASTHI_PARVAT_GHAT);
        locList.add(Loc_SHUBHAS_GHAT);
        locList.add(Loc_GURUKUL_KANGRI);
        locList.add(Loc_FET);

        String[] str = {"Har Ki Pauri", "Mansa Devi Temple", "Bharat Mata Mandir", "Chandi Devi Temple",
                "Vaishno Devi Temple", "Daksha Temple", "Shantikunj", "Gau Ghat",
                "Kushavarta Ghat", "Vishnu Ghat", "Asthi Pravah Ghat", "Subhash Ghat", "GKV", "FET,GKV"};

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", str[position]);
        intent.putExtra("latitude", locList.get(position).getLatitude());
        intent.putExtra("longitude", locList.get(position).getLongitude());
        switch (position) {
            case 0:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/har_ki_pauri.jpg\"/><p style=\"color:#646464;\">Har Ki Pauri is a famous ghat on the banks of the Ganges in Haridwar in Uttarakhand state in India. This revered place is the major landmark of the holy city of Haridwar.<br/>Har Ki Pauri is also the area where thousands of pilgrims converge and the festivities commence during the Kumbha Mela, which takes place every twelve years, and the Ardh Kumbh Mela, which takes place every six years and the Punjabi festival of Vaisakhi, a harvest festival occurring every year in the month of April .</p><h3 style=\"text-align:center; color:#03A9F4;\">The Ganga Aarti</h3><img style=\"width:100%;\" src=\"file:///android_res/drawable/ganga_aarti.jpg\"/><p style=\"color:#646464;\">Each evening at sunset priests perform Ganga Aarti here, when lights are set on the water to drift downstream. A large number of people gather on both the banks of river Ganges to sing its praises. The priests hold large fire bowls in their hands, the gongs in the temples at the Ghat start ringing and the chants flowing out of lips fill the air. People float earthen Diyas, with burning flickers and flowers in them as a symbol of hope and wishes .The golden hues of floral diyas reflected in the river Ganges presents spectacular view.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 1:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/mansa_devi.jpg\"/><p style=\"color:#646464;\">Mansa Devi Temple is an ancient temple that attracts people from both far and near due to its significance. The temple is considered a must visit for the pilgrims going to Haridwar. It enhances the holy tradition of Haridwar which persists in the place from many past centuries. It offers views of the River Ganges and the plains of Haridwar. To reach the shrine one has to either follow the trekking route up to this holy shrine or ride on the recently introduced rope-way service. The rope-way service known as \"Mansa Devi Udankhatola\" was introduced for the benefit of the pilgrims and it caters to the pilgrims also to the nearby located Chandi Devi shrine. The rope-way carries the pilgrims from the lower station directly to the Mansa Devi Temple. The total length of the rope-way is 540 metres (1,770 ft) and the height it covers is 178 metres (584 ft). On a normal day, the temple is open between 8 am and 5 pm, except for lunch closings of 12 pm to 2 pm.</p><br/><img style=\"width:100%;\" src=\"file:///android_res/drawable/mansa_devi_ropeway.jpg\"/><p style=\"color:#646464;\">This temple along with the nearby located Chandi Devi temple is visited by thousands of devotees from various parts of the country, and especially during the Navratra and the Kumbha Mela in Haridwar.<br/>It is said that goddesses Mansa and Chandi, the two forms of goddess Parvati always reside close to each other. This belief can also be found true in other case since near to the Mata Mansa Devi Mandir in Panchkula, Haryana, there is a Chandi Mandir located nearby in Chandigarh.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 2:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/bharat_mata_mandir.jpg\"/><p style=\"color:#646464;\">Bharat Mata Mandir, which is also known as Mother India Temple, is one of the most visited temples located in Haridwar. The temple is dedicated to Bharat Mata by her devoted patriotic nationals. This religious shrine was founded by Swami Satyamitranand Giri. In 1983, this temple was inaugurated by Late Prime Minister, Smt. Indira Gandhi. As the conception of Bharat Mata predates the Partition of India, she is intended to represent \"Aryavarta\", the motherland of Hinduism in Hindu Nationalism, not merely restricted to the secular Republic of India, and Bharat Mata remains a symbol of the \"vision of a unified motherland\" in Hindu nationalist thought. Bharat Mata temples exist in the Mahatma Gandhi Kashi University, inaugurated by Mahatma Gandhi in 1936 and another in Haridwar built in 1983 by Vishwa Hindu Parishad (VHP).</p><br/><p style=\"color:#646464;\">Bharat Mata Mandir Detail:<br/><strong>First Floor :</strong> First floor is dedicated to Bharat Mata (Mother India) it has the statue of Bharat Mata.<br/><strong>Second Floor :</strong>The second floor ‘Shoor Mandir’ is dedicated to the well renowned heroes of India.<br/><strong>Third Floor :</strong>The third floor ‘Matru Mandir’ is dedicated to the achievements of India’s revered women such as Meera Bai, Savitri, Maitri etc. <br/><strong>Fourth Floor :</strong> The fourth floor the great saints from various religions, including Jainism, Sikhism and Buddhism are featured on ‘Sant Mandir’. It is the Sant Mandir and features great saints of the Jainism, Sikhism and Buddhism faith.<br/><strong>Fifth Floor :</strong> The Assembly Hall with walls depicting symbolic coexistence of all religions practiced in India and paintings portraying history and beauty in various provinces of India are situated on the fifth floor. <br/><strong>Sixth Floor :</strong> The various forms of the Goddess of Shakti can be seen on the sixth floor, it is dedicated to Goddess Shakti of the Hindu religion.<br/><strong>Seventh Floor :</strong> The seventh floor is devoted to all incarnations of Lord Vishnu the preserver form of the holy Hindu Trinity.<br/><strong>Eighth Floor :</strong> The uppermost floor features the shrine of Lord Shiva, the supreme god in Hindu tradition.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 3:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/chandi_devi.jpg\"/><p style=\"color:#646464;\">Chandi Devi Temple, Haridwar is a Hindu temple dedicated to Goddess Chandi Devi in the holy city of Haridwar in the Uttarakhand state of India. The temple is situated atop the Neel Parvat on the Eastern summit of the Sivalik Hills, the southernmost mountain chain of the Himalayas. Chandi Devi Temple was built in 1929 by Suchat Singh in his reign as the King of Kashmir. However, the main murti of Chandi Devi at the temple is said to have been installed in the 8th century by Adi Shankaracharya, one of the greatest priests of Hindu religion. The temple also known as Neel Parvat Teerth is one of the Panch Tirth (Five Pilgrimages) located within Haridwar.</p><br/><h3 style=\"text-align:center; color:#03A9F4;\">Significance</h3><p style=\"color:#646464;\">The temple is one of the most ancient temples of India. Thousands of devotees flock to the temple, especially during the festivals of Chandi Chaudas and Navratra and the Kumbha Mela in Haridwar, to seek the blessings of the goddesses who is believed to fulfill their wishes. The temple is a must visit for the pilgrims going to Haridwar.<br/>Very near to the Chandidevi temple, the temple of Anjana, mother of the monkey-god Hanuman is located and devotees visiting Chandi Devi temple also visit this temple. Neeleshwar Temple is also situated at the foot of the Neel Parvat. It is said[citation needed] that Mansa and Chandi, the two forms of goddess Parvati always reside close to each other. The temple of Mansa is exactly on the other side of the hilltop on the Bilwa Parvat on the opposite bank of River Ganges. This belief can also be found true in other case since near to Mata Mansa Devi Mandir in Panchkula, Haryana, there is a Chandi Mandir located nearby in Chandigarh.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 4:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/vaishno_devi.jpg\"/><p style=\"color:#646464;\">The Vaishno Devi Temple lies on the way to the Eight-Storey Bharat Mata Temple a few kilometres outside Haridwar.The Temple is dedicated to Mata Vaishno Devi, and is modelled after the famous temple of the same name in Jammu & Kashmir.<br/>Devotees visited this temple throughout the year. This temple is dedicated to Goddess Vaishno Devi. The leading path of this temple is similar to the temple in Jammu. The path is full of tunnels and caves.Some people believed that the Goddess blesses only those who truly have the intention to implore her blessings from the bottom of their hearts</p><br/><h3 style=\"text-align:center; color:#03A9F4;\">History</h3><p style=\"color:#646464;\">It is very difficult, like any other old shrines, to trace back the history of Vaishno Devi; however geological studies indicate that the holy shrine of Vaishno Devi is almost a million years old. Though the Vedic literature doesn’t refer to any goddess, however one can find the mention of Trikuta hills in Rigveda - the oldest of the four Vedas. <br/>There are different versions of the origin of Vaishno Devi available, but according to the most popular version, the shrine of Vaishno Devi was discovered about 700 years back by Pandit Shridhar. According to the popular belief, Mata once helped in organizing a Bhandaara at Shridhar’s place. But, she had to leave the place to escape Bhairon Nath. As Mata left the place, Shridhar started giving up food in grief and started praying for Mata Vaishno Devi.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 5:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/daksha_mandir.jpg\"/><p style=\"color:#646464;\">Daksheswara Mahadev or Daksha Mahadev temple is a Hindu temple dedicated to Lord Shiva, located in the town of Kankhal, about 4 km from Haridwar, Uttarakhand, India. It is named after King Daksha Prajapati, the father of Sati. Daksha is one of the fourteen Prajapatis, creator deities, who preside over procreation and are the protector of life in Hindu mythology.<br/>The present temple was built by Queen Dhankaur in 1810 and rebuilt in 1962. It is a place of pilgrimage for Shaivaite devotees on Maha Shivaratri.</p><br/><h3 style=\"text-align:center; color:#03A9F4;\">The legend of Daksha</h3><p style=\"color:#646464;\">As mentioned in the Mahabharata and other texts of Hinduism, King Daksha Prajapati, the father of Sati, Shiva's first wife, performed Yagna at the place where the temple is situated. Although Sati felt insulted when her father did not invited Shiva to the ritual, she attended the yagna. She found that Shiva was being spurned by her father and she burnt herself in the Yajna Kunda itself. Shiva got angry and sent his Ganas, the terrible demi-god Virabhadra and Bhadrakali to the ritual. On the direction of Shiva, Virabhadra appeared with Shiva's ganas in the midst of Daksha's assembly like a storm wind and waged a fierce war with the gods and mortals present culminating in the beheading of Daksha, who was later given the head of a goat at the behest of Brahma and other gods. Much of the details of the Ashvamedha Yagna (Horse Sacrifice) of Daksha are found in the Vayu Purana.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 6:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/shanti_kunj.jpg\"/><p style=\"color:#646464;\">Shantikunj is the headquarters of the spiritual and social organisation All World Gayatri Pariwar (AWGP). Established in 1971 at Haridwar, it has emerged over the years as a center of the global movement of Yug Nirman Yojana (Movement for the Reconstruction of the Era) for moral-spiritual regeneration in the light of hoary Indian heritage. Situated at the bank of holy Ganges and between the Shivalik ranges of the Himalayas, its also a place of attraction for tourists and seekers of spiritual guidance.</p><br/><h3 style=\"text-align:center; color:#03A9F4;\">Aim</h3><p style=\"color:#646464;\">Shantikunj is devoted to cultural, ethical, moral and spiritual awakening and national integration. Development of divinity in mankind is the foremost goal and avowed objective. Pledged for national peace, prosperity, amity, love, goodwill and fraternity irrespective of region, religion, faith, caste, creed, sect etc., Shantikunj is a unique abode true to its name. Visitors of all faiths & linkages visit the Ashram (Spiritual Center).<br/>Expansion of national unity, amity and brotherhood and extinction of ignorance, jealousy, hatred, and strife from globe are being attempted by popularizing Gayatri Mantra, Yagya and Sanskaars (sacramental rites), the adoption of which invokes celestial thoughts and inspires for divine deeds. The divinity may be seen firmly fixed in every activity of this holy pilgrimage center.</p><h3 style=\"text-align:center; color:#03A9F4;\">Training camps</h3><p style=\"color:#646464;\">For upliftment of the moral, cultural, spiritual and ethical values, national integration and development of youths, various trainings are organized frequently. Amongst these, the regular ones are:<br/>1. Nine days training of Sanjeewani Vidyaa (Art of Living & Art of Relating training camps)One month training of Yug Shilpi and Parivraajak (Art of Serving & Art of Leading training camps)<br/>2. Three months training for proficiency in Sangeet (music).<br/>3. Occasional trainings of officers, and employees of Govt., local bodies, Corporations, Banks, factories etc. are also organized on: elegant life style, organizational management, efficient working methodology, work culture, glory of labor, health, behavioral science, value of time, inculcation of discipline, duty consciousness, national integration.<br/>4. For the all round development of villages and self-employment generation, 9 days training is also imparted to selected village folk. Training relates to: agriculture, horticulture, herbal production, Dairy and Cottage industries.<br/>5. All these trainings aim at the all round development of the nation & integration of the country by inducing noble sentiments in the minds of individuals by adoption of scientific ways of spirituality, which can provide a remedy for all the ills afflicting the nation.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 7:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/gau_ghat.jpg\"/><p style=\"color:#646464;\">The Gau Ghat is situated on the southern part of the Subhash Ghat. Mythologically, the cow is known as \"Kamadhenu\". That fulfills the wishes of the desires. The people reach at this site to grant forgiveness for the sin of cow killing for foods. These formal events were celebrated with the holy act of a donation of a cow. The act of killing of a cow is similar, of killing to a Brahmin. On any religious occasion people crowds the Gau Ghat for prayer. There is a fact that the ashes of Indira Gandhi, Jawaharlal Nehru and Mahatma Gandhi were blew at this ghat.</p><br/><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 8:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/kush_ghat.jpg\"/><p style=\"color:#646464;\">Kushavarta Ghat, located at a distance of 0.5 km from Har Ki Pauri, was constructed by a Maratha queen Ahilyabai Holkar. It is one of the ideal religious destinations for devotees who wish to perform Shraddha rites for the departed souls.<br/><br/>According to a popular belief, it is said that this ghat is related to Dattatreya, who was a great saint of the ancient period. It is believed that he had made a visit to this ghat several times and also mediated on this ghat. According to the chronicles of history, it is stated that it is this ghat where Dattatreya offered his penance by standing on one foot for thousand years.</p><br/><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 9:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/vishnu_ghat.jpg\"/><p style=\"color:#646464;\">Vishnu Ghat, named after Lord Vishnu, is one of the most visited ghats located in Haridwar. Believed to be the same place where Lord Vishnu bathed, it is said that a dip in the holy water of this ghat, washes away all the sins. Owing to the immense faith in Lord Vishnu by the people of Haridwar, devotees as well as tourists visit this land to take a dip in the ghat.</p><br/><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</p></br></strong></body></html>");
                break;
            case 10:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/ashthi_pravah.jpg\"/><p style=\"color:#646464;\">The Asthi Parvath Ghat is located in the south of the Ganga temple. Peoples float the ashes of the dead persons in the sacred water of Ganga at this Ghat. They believe that as the 60000 thousand sons of Sagara got salvation in the cool water of Ganga. People first do the prayer and then they blow the ashes of their kin in the river. When the cannel is closed the beggars started finding the coins and gold which is blown in the river with the ashes.</p><br/><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 11:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/subhash_ghat.jpg\"/><p style=\"color:#646464;\">The Subhash Ghat is close to the Harki Pauri Ghat with a statue of Netaji Subhash Chandra Bose, one of the great freedom fighter of India. There is a voluntarily organized Sewa Samiti which provides helps to the pilgrims as a dispensary.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.</br>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</p></strong></body></html>");
                break;
            case 12:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/gkv.jpg\"/><p style=\"color:#646464;\">Gurukula Kangri University or Gurukula Kangri Vishwavidyalaya is a Deemed to be University  u/s 3 of UGC Act 1956 located in the city of Haridwar in the Indian state of Uttarakhand.</p><h3 style=\"text-align:center; color:#03A9F4;\">Establishments</h3><p style=\"color:#646464;\">Gurukula Kangri Vishwavidyalaya was founded on March 4, 1902 by Swami Shraddhanandaji with the sole aim to revive the ancient Indian Gurukula System of education, on the bank of Ganges at a distance of about 6 km. from Hardwar and about 200 km. from Delhi. This institution was established with the objective of providing an indigenous alternative to Lord Macaulay’s education policy by imparting education in the areas of vedic literature, Indian philosophy, Indian culture, modern sciences and research.It is a deemed to be university fully funded by UGC/Govt. of India.</p><h3 style=\"text-align:center; color:#03A9F4;\">Campuses</h3><p style=\"color:#646464;\">The university has separate campuses for boys and girls.<br/>1. Main Campus, Gurukul Kangri University, Haridwar (Boys only).<br/>2. Faculty of Engineering & Technology Campus, Haridwar (Boys only)<br/>3. Kanya Gurukul Mahavidhyalaya, Haridwar (Girls only)<br/>4. Kanya Gurukul Mahavidhyalaya, Dehradun (Girls only)</p><h3 style=\"text-align:center; color:#03A9F4;\">Achievements</h3><p style=\"color:#646464;\"><strong>(a) Deemed University Status :</strong> The distinguished services of this institution to the nation were recognized when it was given the status of Deemed to be University in 1962 by University Grants Commission.<br/><strong>(b) Four Star Status :</strong> National Accreditation and Assessment Council (NAAC) has awarded four star (****) status to the Vishwavidyalaya in 2002.<br/><strong>(c) Centenary Year :</strong> Vishwavidyalaya celebrated year 2002 as its centenary year. The Vishwavidyalaya has had phenomenal growth over the past 100 years and now offers 20 UG courses and 17 PG courses in different disciplines.Research programmes leading to the degree of Ph.D. are being run in the fields of Vedic literature, Sanskrit literature, Philosophy, Hindi, English, Psychology, Ancient Indian History Culture & Archaeology, Environmental Science, Physics, Chemistry, Mathematics, Human Consciousness and Yogic Sciences, Botany, Zoology, Microbiology, Computer sciences and Management Studies.<br/><strong>(d) Membership :</strong> Gurukula Kangri Vishwavidyalaya is a Registered autonomous institute. All the degrees conferred by Vishwavidyalaya are recognized by UGC and AICTE wherever required. Gurukula Kangri Vishwavidyalaya is a pride member of Association of Indian Universities and Association of Commonwealth Universities.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
            case 13:
                intent.putExtra("url", "<html><body><img style=\"width:100%;\" src=\"file:///android_res/drawable/fet_gkv.jpg\"/><h3 style=\"text-align:center; color:#03A9F4;\">Establishments</h3><p style=\"color:#646464;\">The Faculty of Engineering & Technology was established with a view to provide technical education in surroundings of spiritual and Gurukula system of education. Gurukula Kangri Vishwavidyalaya, Haridwar is known in India and abroad for the process of character building of the students through their moral and physical development. Keeping the same ideals in mind, management of the Vishwavidyalaya felt the need of technocrats with strong moral character, superior knowledge, who can serve the nation during this era of transition from materialism to a balance of materialism and spiritualism.<br/>The Faculty of Engineering & Technology was established year 2000 at present it is running B.Tech. in Computer Science & Engineering , Electronics & Communication Engineering ,Electrical Engineering and Mechanical Engineering.</p><br/><p style=\"color:#646464;\">FET is accredited with TCS and major companies like Infosys, Adobe, Tech. Mahindra, HCL, Mahindra Satyam, J.K.Technosoft, Infogain have recruited our students. Beside teaching FET conducts regular extra curricular activities like technical festival, sports etc.Students of FET enjoy the facility of state of art gymnasium situated at university stadium.</p><h3 style=\"text-align:center; color:#03A9F4;\">Departments</h3><p style=\"color:#646464;\">Faculty has following seven departments:<br/>1. Department of Computer Sc. & Engineering.<br/>2. Department of Electronics Communication & Engineering.<br/>3. Department of Electrical Engineering.<br/>4. Department of Mechanical Engineering.<br/>5. Department of Applied Physics.<br/>6. Department of Applied Chemistry.<br/>7. Department of Applied Mathematics.</p><strong><p style=\"color:#646464;\">Keep Ganga Clean.<br/>Take a pledge to protect your local water bodies and environment, and to inspire your friends, family and community to do the same.</br></br></br></br></p></strong></body></html>");
                break;
        }
        startActivity(intent);
    }
}