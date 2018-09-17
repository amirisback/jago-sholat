package id.duglegir.jagosholat.Controller.ClassHelper;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.DoaShalat;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.NiatShalat;

public class JSONHelper {

    private static final String BASE_JSON_REPSONSE ="{\n" +
            "      \"niat_shalat\":[\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Shubuh\",\n" +
            "            \"bacaan\":\"اُصَلِّيْ فَرْضَ الصُّبْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ اْلقِبْلَةِ اَدَاءً لِلَّهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"2 Raka'at\",\n" +
            "            \"latin\":\"Ushallii fardhal shubhi rak'ataini mustaqbilall qiblati adaa-an lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu Shubuh 2 raka'at menghadap kiblat karena Allah Ta'ala\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Dzuhur\",\n" +
            "            \"bacaan\":\"اُصَلِّى فَرْضَ الظُّهْرِاَرْبَعَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً ِللهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"4 Raka'at\",\n" +
            "            \"latin\":\"Ushallii fardhodl dhuhri arba'a raka'aatim mustaqbilal qiblati adaa-an lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu Dzuhur 4 raka'at menghadap kiblat karena Allah Ta'al\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Ashar\",\n" +
            "            \"bacaan\":\"اُصَلِّى فَرْضَ الْعَصْرِاَرْبَعَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً ِللهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"4 Raka'at\",\n" +
            "            \"latin\":\"Ushallii fardhol 'ashri arba'a raka'aatim mustaqbilal qiblati adaa-an lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu 'Ashar 4 raka'at menghadap kiblat karena Allah Ta'ala\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Maghrib\",\n" +
            "            \"bacaan\":\"اُصَلِّى فَرْضَ الْمَغْرِبِ ثَلاَثَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً ِللهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"3 Raka'at\",\n" +
            "            \"latin\":\"Ushallii fardhol maghribi tsalaata raka'aatim mustaqbilal qiblati adaa-an lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu Maghrib 3 raka'at menghadap kiblat karena Allah Ta'ala\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Isya\",\n" +
            "            \"bacaan\":\"اُصَلِّى فَرْضَ الْعِشَاءِ اَرْبَعَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً ِللهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"4 Raka'at\",\n" +
            "            \"latin\":\"Ushallii fardhol 'isyaa-i arba'a raka'aatim mustaqbilal qiblati adaa-an lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu Isya 4 raka'at menghadap kiblat karena Allah Ta'ala\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"niat\":\"Shalat Jum'at\",\n" +
            "            \"bacaan\":\"اُصَلِّيْ فَرْضَ الجُمْعَةِ رَكْعَتَيْنِ مُسْتَقْبِلَ اْلقِبْلَةِ اَدَاءً مَاْمُوْمًا لِلَّهِ تَعَالَى\",\n" +
            "            \"rakaat\":\"2 Rakaat\",\n" +
            "            \"latin\":\"Ushallii fardlol jum'ati rak'ataini mustaqbilal qiblati adaa-an ma-muuman lillaahi ta'aala\",\n" +
            "            \"arti\":\"Artinya : \n \t\t\tSaya niat melakukan shalat fardhu Jum'at 2 raka'at, menghadap kiblat sebagai ma'mum, karena Allah Ta'ala\"\n" +
            "         }\n" +
            "      ],\n" +
            "      \"doa_shalat\":[\n" +
            "         {\n" +
            "            \"bacaan\":\"َبِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِ. اَلْحَمْدُ ِللهِ رَبِّ الْعَالَمِيْنَ. حَمْدًا يُوَافِىْ نِعَمَهُ وَيُكَافِئُ مَزِيْدَهُ. يَارَبَّنَالكَ الْحَمْدُ وَلَكَ الشُّكْرُ كَمَا يَنْبَغِىْ لِجَلاَلِ وَجْهِكَ وَعَظِيْمِ سُلْطَانِك\",\n" +
            "            \"latin\":\"BISMILLAAHIRRAHMAANIRRAHIIM. ALHAMDU LILLAAHI RABBIL 'AALAMIIN. HAMDAY YU-WAAFII NI'AMAHUU WA YUKAAFI'U MAZIIDAH. YAA RABBANAA LAKALHAMDU WA LAKASY SYUKRU KA-MAA YAMBAGHIILIJALAALIWAJHIKA WA 'AZHIIMISUL-THAANIK\",\n" +
            "            \"arti\":\"Dengan nama Allah Yang Maha Pengasih Lagi Maha Penyayang. Segala puji bagi Allah Tuhan Semesta Alam. Pujian yang sebanding dengan nikmat-nikmatNya dan menjamin tambahannya. Wahai Tuhan kami, bagi-Mu-lah segala puji, dan bagi-Mu-lah segalah syukur, sebagaimana layak bagi keluhuran zat-Mu dan keagungan kekuasaan-Mu\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"اَللهُمَّ صَلِّ وَسَلِّمْ عَلَى سَيِّدِنَا مُحَمَّدٍ وَعَلى آلِ سَيِّدِنَا مُحَمَّدٍ. صَلاَةً تُنْجِيْنَابِهَا مِنْ جَمِيْعِ اْلاَهْوَالِ وَاْلآفَاتِ. وَتَقْضِىْ لَنَابِهَا جَمِيْعَ الْحَاجَاتِ.وَتُطَهِّرُنَا بِهَا مِنْ جَمِيْعِ السَّيِّئَاتِ. وَتَرْفَعُنَابِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ. وَتُبَلِّغُنَا بِهَا اَقْصَى الْغَيَاتِ مِنْ جَمِيْعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَبَعْدَ الْمَمَاتِ اِنَّهُ سَمِيْعٌ قَرِيْبٌ مُجِيْبُ الدَّعَوَاتِ وَيَاقَاضِىَ الْحَاجَات\",\n" +
            "            \"latin\":\"ALLAAHUMMA SHALLIWASALLIM 'ALAA SAYYIDINAA MUHAMMADIW WA 'ALAA AALI SAYYIDINAA MUHAMMAD. SHALA ATAN TUN AJIHNAA BÍHAA MINJAMII'IL AHWAALI WAL AAFAAT. WA TAQDHII LANAA BIHAA JAMII'AL HAAJAAT. WA TUTHAHHIRUNAA BIHAA MIN JAMII'IS SAYYI'AAT. W ATARFA ' UN A A BIHAA 'INDAKA ' A'LADDARAJAAT. WA TUBALLIGHUNAA BIHAA AQSHAL GHAAYAATI MIN JAMII'IL KHAIRAATIFIL HAYAATIWA BA'DAL MAMAAT. INNAHU SAMII'UN QARIIBUM MUJIIBUD DA'AWAAT WAYAA QAADHIYAL HAAJAAT.\",\n" +
            "            \"arti\":\"Wahai Allah, limpahkanlah rahmat dan kesejahteraan kepada penghulu kami, Nabi Muhammad dan keluarganya, yaitu rahmat yang dapat menyelamatkan kami dari segala ketakutan dan penyakit, yang dapat memenuhi segala kebutuhan kami, yang dapat mensucikan diri kami dari segala keburukan, yang dapat mengangkat derajat kami ke derajat tertinggi di sisi-Mu, dan dapat menyampaikan kami kepada tujuan maksimal dari segala kebaikan, baik semasa hidup maupun sesudah mati. Sesunggunya Dia (Allah) Maha Mendengar, Maha Dekat, lagi Maha Memperkenankan segala doa dan permohonan. Wahai Dzat yang Maha Memenuhi segala kebutuhan Hamba-Nya.\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"اَللهُمَّ اِنَّا نَسْئَلُكَ سَلاَمَةً فِى الدِّيْنِ وَالدُّنْيَا وَاْلآخِرَةِ وَعَافِيَةً فِى الْجَسَدِ وَصِحَّةً فِى الْبَدَنِ وَزِيَادَةً فِى الْعِلْمِ وَبَرَكَةً فِى الرِّزْقِ وَتَوْبَةً قَبْلَ الْمَوْتِ وَرَحْمَةً عِنْدَ الْمَوْتِ وَمَغْفِرَةً بَعْدَ الْمَوْتِ. اَللهُمَّ هَوِّنْ عَلَيْنَا فِىْ سَكَرَاتِ الْمَوْتِ وَالنَّجَاةَ مِنَ النَّارِ وَالْعَفْوَ عِنْدَ الْحِسَاب\",\n" +
            "            \"latin\":\"ALLAAHUMMA INNAA NAS'ALUKA SALAAMATAN FTDDIINI WADDUN-YAA WAL AAKHIRAH. WA 'AAFIYA-TAN FIL JASADI WA SHIHHATAN FIL BADANI WA ZIYAADATAN FIL 'ILMI WA BARAKATAN FIRRIZQI WA TAUB ATAN QABLAL MAUT WA RAHM ATAN 'INDALMAUT WA MAGHFIRATAN BA'D AL MAUT. ALLAAHUMMA HAWWIN 'ALAINAA FII SAKARAATIL MAUT WAN NAJAATA MINAN NAARI WAL 'AFWA 'INDAL HISAAB.\",\n" +
            "            \"arti\":\"Wahai Allah! Sesungguhnya kami memohon kepadaMu, kesejahteraan dalam agama, dunia dan akhirat, keafiatan jasad, kesehatan badan, tambahan ilmu, keberkahan rezeki, taubat sebelum datang maut, rahmat pada saat datang maut, dan ampunan setelah datang maut. Wahai Allah! Permudahkanlah kami dalam menghadapi sakaratul maut, (Berilah kami) keselamatan dari api neraka, dan ampunan pada saat dilaksanakan hisab.\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"اَللهُمَّ اِنَّا نَعُوْذُبِكَ مِنَ الْعَجْزِ وَالْكَسَلِ وَالْبُخْلِ وَالْهَرَمِ وَعَذَابِ الْقَبْر\",\n" +
            "            \"latin\":\"ALLAAHUMMA INNAA NA'UUDZU BIKA MINAL 'AJZI WAL KASALI WAL BUKHLI WAL HARAMI WA 'ADZAABIL QABRI\",\n" +
            "            \"arti\":\"Wahai Allah! Sesungguhnya kami berlindung kepadaMu dari sifat lemah, malas, kikir, pikun dan dari azab kubur\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"اَللهُمَّ اِنَّا نَعُوْذُبِكَ مِنْ عِلْمٍ لاَيَنْفَعُ وَمِنْ قَلْبٍ لاَيَخْشَعُ وَمِنْ نَفْسٍ لاَتَشْبَعُ وَمِنْ دَعْوَةٍ لاَيُسْتَجَابُ لَهَا\",\n" +
            "            \"latin\":\"ALLAAHUMMAINNAA NA'UUDZU BIKA MIN 'ILMIN LAA YANFA' W AMIN QALBIN LAA YAKHSYA' W AMIN NAFSIN LAA TASYBA' WAMIN DA'WATIN LAA YUSTAJAABU LAHAA\",\n" +
            "            \"arti\":\"Wahai Allah! Sesungguhnya kami berlindung kepadaMu dari ilmu yang tidak bermanfaat, dari hati yang tidak khusyu', dari jiwa yang tidak kenal puas, dan dari doa yang tak terkabul\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"رَبَّنَااغْفِرْلَنَا ذُنُوْبَنَا وَلِوَالِدِيْنَا وَلِمَشَايِخِنَا وَلِمُعَلِّمِيْنَا وَلِمَنْ لَهُ حَقٌّ عَلَيْنَا وَلِمَنْ اَحَبَّ وَاَحْسَنَ اِلَيْنَا وَلِكَافَّةِ الْمُسْلِمِيْنَ اَجْمَعِيْن\",\n" +
            "            \"latin\":\"RABBANAGH FIRLANAA DZUNUUBANAA WA LIWAA-LIDIINAA WALIMASYAAYIKHINAA WA LIMU'ALLI-MIENAA WA LIMAN LAHUU H AQQUN' ALAIN AA WA LIM AN AHABBA WA AHSANA ILAINAA WA LIKAAFFATIL MUS LIMUN A AJMA'IIN.\",\n" +
            "            \"arti\":\"Wahai Tuhan Kami, ampunilah dosa-dosa kami, dosa-dosa orang tua kami, para sesepuh kami, para guru kami, orang-orang yang mempunyai hak atas kami, orang-orang yang cinta dan berbuat baik kepada kami, dan seluruh umat islam\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"رَبَّنَا تَقَبَّلْ مِنَّا اِنَّكَ اَنْتَ السَّمِيْعُ الْعَلِيْمُ وَتُبْ عَلَيْنَا اِنَّكَ اَنْتَ التَّوَّابُ الرَّحِيْم\",\n" +
            "            \"latin\":\"RABBANAA TAQABBAL MINNAA INNAKA ANTAS SAMII'UL 'ALIIM, WA TUB 'ALAINAA INNAKA ANTAT TA WWA ABUR RAHIIM\",\n" +
            "            \"arti\":\"Wahai Tuhan kami, perkenankanlah (permohonan) dari kami, sesungguhnya Engkau Maha Mendengar Lagi Maha Mengetahui. Dan terimalah taubat kami, sesungguhnya Engkau Maha Menerima Taubat lagi Maha Penyayang.\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"رَبَّنَا أَتِنَا فِى الدُّنْيَا حَسَنَةً وَفِي اْلأَخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّار\",\n" +
            "            \"latin\":\"RABBANAA AATINAA FIDDUNNYAA HASANAH, WA FIL AAKHIRATI HASANAH, WAQINAA ‘ADZAA BAN NAAR\",\n" +
            "            \"arti\":\"Ya Tuhan kami, berilah kami kebaikan hidup di dunia dan kebaikan hidup di akhirat, dan jagalah kami dari siksa api neraka\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"bacaan\":\"وَصَلَّى اللهُ عَلى سَيِّدِنَا مُحَمَّدٍ وَعَلى آلِهِ وَصَحْبِهِ وَسَلَّمَ وَالْحَمْدُ ِللهِ رَبِّ الْعَالَمِيْن\",\n" +
            "            \"latin\":\"WASHALLALLAAHU 'ALAA SAYYIDINAA MUHAMMA-DIN WA'ALAA AALIHIWA SHAHBIHIIWA SALLAM, WAL HAMDU LILLAAHIRABBIL 'AALAMIIN\",\n" +
            "            \"arti\":\"Semoga Allah memberikan rahmat dan kesejahteraan kepada penghulu kami, Nabi Muhammad, keluarga dan sahabatnya dan segala puji bagi Allah, Tuhan Semesta Alam\"\n" +
            "         }\n" +
            "      ]\n" +
            "}";

    public JSONHelper() {
    }

    public static ArrayList<DoaShalat> extractDoaShalat(){
        ArrayList<DoaShalat> arrayListDoaShalat = new ArrayList<>();
        try {
            // -------------------------------------------------------------------------------------
            JSONObject root = new JSONObject(BASE_JSON_REPSONSE);
            JSONArray arrayDoaShalat = root.getJSONArray("doa_shalat");
            // -------------------------------------------------------------------------------------
            for (int i = 0 ; i < arrayDoaShalat.length() ; i++){
                JSONObject mDoaShalat = arrayDoaShalat.getJSONObject(i);
                // ---------------------------------------------------------------------------------
                String arabDoa = mDoaShalat.getString("bacaan");
                String latin = mDoaShalat.getString("latin");
                String arti = mDoaShalat.getString("arti");
                // ---------------------------------------------------------------------------------
                DoaShalat doaShalat = new DoaShalat(arabDoa,latin,arti);
                arrayListDoaShalat.add(doaShalat);
                // ---------------------------------------------------------------------------------
            }

        }catch (Exception e){
            Log.e("JSONHelper", "Problem parsing the earthquake JSON results", e);
        }

        return arrayListDoaShalat;
    }


    public static ArrayList<NiatShalat> extractNiatShalat(){
        ArrayList<NiatShalat> arrayListNiatShalat = new ArrayList<>();
        try {
            // -------------------------------------------------------------------------------------
            JSONObject root = new JSONObject(BASE_JSON_REPSONSE);
            JSONArray arrayNiatShalat = root.getJSONArray("niat_shalat");
            // -------------------------------------------------------------------------------------
            for (int i= 0 ; i<arrayNiatShalat.length();i++){
                JSONObject mNiatShalat = arrayNiatShalat.getJSONObject(i);
                // ---------------------------------------------------------------------------------
                String niat = mNiatShalat.getString("niat");
                String arabDoa = mNiatShalat.getString("bacaan");
                String rakaat = mNiatShalat.getString("rakaat");
                String latin = mNiatShalat.getString("latin");
                String arti = mNiatShalat.getString("arti");
                // ---------------------------------------------------------------------------------
                NiatShalat niatShalat = new NiatShalat(rakaat,niat,arabDoa,latin,arti);
                arrayListNiatShalat.add(niatShalat);
                // ---------------------------------------------------------------------------------
            }

        }catch (Exception e){
            Log.e("JSONHelper", "Problem parsing the earthquake JSON results", e);
        }

        return arrayListNiatShalat;
    }
}
