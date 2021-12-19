package com.bilgeadam.marathon.view;

import com.bilgeadam.marathon.controller.ArtistController;
import com.bilgeadam.marathon.controller.CDController;
import com.bilgeadam.marathon.controller.DVDController;
import com.bilgeadam.marathon.controller.VinylController;
import com.bilgeadam.marathon.entity.ArtistEntity;
import com.bilgeadam.marathon.entity.CDEntity;
import com.bilgeadam.marathon.entity.DVDEntity;
import com.bilgeadam.marathon.entity.VinylEntity;
import com.bilgeadam.marathon.enums.EDVDQuality;
import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.enums.EVinlyDiameter;
import com.bilgeadam.marathon.enums.EVinylRPM;
import com.bilgeadam.marathon.model.Artist;
import com.bilgeadam.marathon.util.McUtils;

public class AdminPanel {

    public void showAdminPanel() {
        System.out.println();
        int choice = 0;
        do {
            choice = McUtils.readInt("Lütfen ne yapmak istediğinizi seçiniz:\n" +
                    "\t1) Albüm oluştur\n" +
                    "\t2) Albüm güncelle\n" +
                    "\t3) Albüm sil\n" +
                    "\t4) Default Database oluştur.\n" +
                    "\t99) Exit\n");
            switch (choice) {
                case 1 -> createAlbum();
                case 2 -> updateAlbum();
                case 3 -> deleteAlbum();
                case 4 -> addDefaultDatas();
                case 99 -> System.exit(-1);
            }

        } while (true);
    }

    private void deleteAlbum() {
        System.out.println();
        int choice = 0;
        ArtistEntity artist = new ArtistEntity();
        ArtistController artistController = new ArtistController();
        choice = McUtils.readInt("Oluşturmak istediğiniz albümün tipi:\n" + "\t1) CD\n"
                + "\t2) DVD\n" + "\t3) Vinyl\n" + "\t99) Exit\n");

        switch (choice) {
            case 1 -> {
                CDEntity cdEntity = new CDEntity();
                CDController controller = new CDController();
                controller.delete(cdEntity);
            }
            case 2 -> {
                DVDEntity dvdEntity = new DVDEntity();
                DVDController dvdController = new DVDController();
                dvdController.delete(dvdEntity);
            }
            case 3 -> {
                VinylEntity vinylEntity = new VinylEntity();
                VinylController vinylController = new VinylController();
                vinylController.delete(vinylEntity);
            }
        }
    }

    private void updateAlbum() {
        System.out.println();
        int choice = 0;
        ArtistEntity artist = new ArtistEntity();
        ArtistController artistController = new ArtistController();
        choice = McUtils.readInt("Değiştirmek istediğiniz albümün tipi:\n" + "\t1) CD\n"
                + "\t2) DVD\n" + "\t3) Vinyl\n" + "\t99) Exit\n");

        switch (choice) {
            case 1 -> {
                CDEntity cdEntity = new CDEntity();
                CDController controller = new CDController();
                controller.update(cdEntity);
            }
            case 2 -> {
                DVDEntity dvdEntity = new DVDEntity();
                DVDController dvdController = new DVDController();
                dvdController.update(dvdEntity);
            }
            case 3 -> {
                VinylEntity vinylEntity = new VinylEntity();
                VinylController vinylController = new VinylController();
                vinylController.update(vinylEntity);
            }
        }
    }

    public void createAlbum() {
        System.out.println();
        int choice = 0;
        ArtistEntity artist = new ArtistEntity();
        ArtistController artistController = new ArtistController();
        choice = McUtils.readInt("Oluşturmak istediğiniz albümün tipi:\n" + "\t1) CD\n"
                + "\t2) DVD\n" + "\t3) Vinyl\n" + "\t99) Exit\n");
        switch (choice) {
            case 1 -> {
                CDEntity cdEntity = new CDEntity();
                CDController cdController = new CDController();
                cdEntity.setName(McUtils.readString("Albümün ismi"));
                cdEntity.setPrice(McUtils.readDouble("Albümün fiyatı"));
                String isDiscountCD = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountCD.equalsIgnoreCase("Y")) {
                    cdEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    cdEntity.setDiscountRate(0);
                }
                setAlbumGenres(cdEntity);
                cdController.setDiscountedPrice(cdEntity);
                artist.setFirstName(McUtils.readString("Artistin adı"));
                artist.setLastName(McUtils.readString("Artistin soyadı"));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi"));
                cdEntity.setArtist(artist);
                artistController.create(artist);
                cdController.create(cdEntity);
            }
            case 2 -> {
                DVDEntity dvdEntity = new DVDEntity();
                DVDController dvdController = new DVDController();
                dvdEntity.setName(McUtils.readString("Albümün ismi: "));
                dvdEntity.setPrice(McUtils.readDouble("Albümün fiyatı: "));
                String isDiscountDVD = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountDVD.equalsIgnoreCase("Y")) {
                    dvdEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    dvdEntity.setDiscountRate(0);
                }
                setAlbumGenres(dvdEntity);
                setAlbumQuality(dvdEntity);
                dvdController.setDiscountedPrice(dvdEntity);
                artist.setFirstName(McUtils.readString("Artistin adı: "));
                artist.setLastName(McUtils.readString("Artistin soyadı: "));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi: "));
                dvdEntity.setArtist(artist);
                artistController.create(artist);
                dvdController.create(dvdEntity);
            }
            case 3 -> {
                VinylEntity vinylEntity = new VinylEntity();
                VinylController vinylController = new VinylController();
                vinylEntity.setName(McUtils.readString("Albümün ismi: "));
                vinylEntity.setPrice(McUtils.readDouble("Albümün fiyatı: "));
                String isDiscountVinyl = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountVinyl.equalsIgnoreCase("Y")) {
                    vinylEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    vinylEntity.setDiscountRate(0);
                }
                setAlbumGenres(vinylEntity);
                setVinylDiameter(vinylEntity);
                setVinylSpeed(vinylEntity);
                artist.setFirstName(McUtils.readString("Artistin adı: "));
                artist.setLastName(McUtils.readString("Artistin soyadı: "));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi: "));
                vinylEntity.setArtist(artist);
                artistController.create(artist);
                vinylController.create(vinylEntity);
            }
            case 99 -> System.exit(-1);
        }
    }

    private void setVinylSpeed(VinylEntity vinylEntity) {
        int qualityChoice = (McUtils.readInt("Plağın hızı nedir?\n " +
                "1) LOW\" \n" +
                " 2) MEDIUM\"\n" +
                " 3) HIGH\"\n"));

        switch (qualityChoice) {
            case 1 -> vinylEntity.setSpeed(EVinylRPM.LOW);
            case 2 -> vinylEntity.setSpeed(EVinylRPM.MEDIUM);
            case 3 -> vinylEntity.setSpeed(EVinylRPM.HIGH);
        }
    }

    private void setVinylDiameter(VinylEntity vinylEntity) {
        int qualityChoice = (McUtils.readInt("Plağın boyutu nedir?\n " +
                "1) 7\" \n" +
                " 2) 10\"\n" +
                " 3) 12\"\n"));

        switch (qualityChoice) {
            case 1 -> vinylEntity.setDiameter(EVinlyDiameter.SEVEN);
            case 2 -> vinylEntity.setDiameter(EVinlyDiameter.TEN);
            case 3 -> vinylEntity.setDiameter(EVinlyDiameter.TWELVE);
        }
    }

    public void setAlbumGenres(CDEntity cdEntity) {

        int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                "1) Blues \n" +
                " 2) Jazz\n" +
                " 3) Rock\n" +
                " 4) Country\n" +
                " 5) Dance\n" +
                " 6) Hip Hop\n" +
                "7) Soul\n"));
        switch (genreChoice) {
            case 1 -> cdEntity.setGenre(EGenre.BLUES);
            case 2 -> cdEntity.setGenre(EGenre.JAZZ);
            case 3 -> cdEntity.setGenre(EGenre.ROCK);
            case 4 -> cdEntity.setGenre(EGenre.COUNTRY);
            case 5 -> cdEntity.setGenre(EGenre.DANCE);
            case 6 -> cdEntity.setGenre(EGenre.HIPHOP);
            case 7 -> cdEntity.setGenre(EGenre.SOUL);
        }
    }

    public void setAlbumGenres(DVDEntity dvdEntity) {

        int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                "1) Blues \n" +
                " 2) Jazz\n" +
                " 3) Rock\n" +
                " 4) Country\n" +
                " 5) Dance\n" +
                " 6) Hip Hop\n" +
                " 7) Soul\n"));
        switch (genreChoice) {
            case 1 -> dvdEntity.setGenre(EGenre.BLUES);
            case 2 -> dvdEntity.setGenre(EGenre.JAZZ);
            case 3 -> dvdEntity.setGenre(EGenre.ROCK);
            case 4 -> dvdEntity.setGenre(EGenre.COUNTRY);
            case 5 -> dvdEntity.setGenre(EGenre.DANCE);
            case 6 -> dvdEntity.setGenre(EGenre.HIPHOP);
            case 7 -> dvdEntity.setGenre(EGenre.SOUL);
        }
    }

    private void setAlbumQuality(DVDEntity dvdEntity) {
        int qualityChoice = (McUtils.readInt("Albümün kalitesi nedir? \n" +
                "1) LOW \n" +
                " 2) MEDIUM\n" +
                " 3) HIGH\n"));

        switch (qualityChoice) {
            case 1 -> dvdEntity.setQuality(EDVDQuality.LOW);
            case 2 -> dvdEntity.setQuality(EDVDQuality.MEDIUM);
            case 3 -> dvdEntity.setQuality(EDVDQuality.HIGH);
        }
    }

    public void setAlbumGenres (VinylEntity vinylEntity){

            int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                    " 1) Blues \n" +
                    " 2) Jazz\n" +
                    " 3) Rock\n" +
                    " 4) Country\n" +
                    " 5) Dance\n" +
                    " 6) Hip Hop\n" +
                    "7) Soul\n"));
        switch (genreChoice) {
            case 1 -> vinylEntity.setGenre(EGenre.BLUES);
            case 2 -> vinylEntity.setGenre(EGenre.JAZZ);
            case 3 -> vinylEntity.setGenre(EGenre.ROCK);
            case 4 -> vinylEntity.setGenre(EGenre.COUNTRY);
            case 5 -> vinylEntity.setGenre(EGenre.DANCE);
            case 6 -> vinylEntity.setGenre(EGenre.HIPHOP);
            case 7 -> vinylEntity.setGenre(EGenre.SOUL);
        }
    }

    public void addDefaultDatas() {
        ArtistEntity artist1 = new ArtistEntity();
        CDEntity cdEntity1 = new CDEntity();
        artist1.setFirstName("Nirvana");
        artist1.setDescription("American Rock Band.");
        cdEntity1.setName("Nevermind");
        cdEntity1.setPrice(30);
        cdEntity1.setDiscountRate(0.15);
        cdEntity1.setGenre(EGenre.ROCK);
        cdEntity1.setArtist(artist1);
        CDEntity cdEntity2 = new CDEntity();
        cdEntity2.setName("In Utero");
        cdEntity2.setPrice(25);
        cdEntity2.setGenre(EGenre.ROCK);
        cdEntity2.setArtist(artist1);

        ArtistEntity artist2 = new ArtistEntity();
        DVDEntity dvdEntity1 = new DVDEntity();
        artist2.setFirstName("Muse");
        artist2.setDescription("English Rock Band");
        dvdEntity1.setName("Showbiz");
        dvdEntity1.setPrice(40);
        dvdEntity1.setDiscountRate(0.1);
        dvdEntity1.setGenre(EGenre.ROCK);
        dvdEntity1.setQuality(EDVDQuality.HIGH);
        dvdEntity1.setArtist(artist2);
        DVDEntity dvdEntity2 = new DVDEntity();
        dvdEntity2.setName("Absolution");
        dvdEntity2.setPrice(35);
        dvdEntity2.setGenre(EGenre.ROCK);
        dvdEntity2.setQuality(EDVDQuality.HIGH);
        dvdEntity2.setArtist(artist2);

        ArtistEntity artist3 = new ArtistEntity();
        artist3.setFirstName("Miles");
        artist3.setLastName("Davis");
        artist3.setDescription("American trumpeter");
        VinylEntity vinylEntity1 = new VinylEntity();
        vinylEntity1.setName("King of Blue");
        vinylEntity1.setPrice(45);
        vinylEntity1.setDiscountRate(0.05);
        vinylEntity1.setGenre(EGenre.JAZZ);
        vinylEntity1.setDiameter(EVinlyDiameter.SEVEN);
        vinylEntity1.setSpeed(EVinylRPM.HIGH);
        vinylEntity1.setArtist(artist3);
        VinylEntity vinylEntity2 = new VinylEntity();
        vinylEntity2.setName("Miles In Tokyo");
        vinylEntity2.setPrice(40);
        vinylEntity2.setDiscountRate(0.12);
        vinylEntity2.setGenre(EGenre.JAZZ);
        vinylEntity2.setDiameter(EVinlyDiameter.TEN);
        vinylEntity2.setSpeed(EVinylRPM.MEDIUM);
        vinylEntity2.setArtist(artist3);

        ArtistEntity artist4 = new ArtistEntity();
        artist4.setFirstName("Kenny");
        artist4.setLastName("Rogers");
        artist4.setDescription("American singer, songwriter, musician, actor, record producer, and entrepreneur.");
        VinylEntity vinylEntity3 = new VinylEntity();
        vinylEntity3.setName("Christmas");
        vinylEntity3.setPrice(5);
        vinylEntity3.setGenre(EGenre.COUNTRY);
        vinylEntity3.setDiameter(EVinlyDiameter.TWELVE);
        vinylEntity3.setSpeed(EVinylRPM.LOW);
        vinylEntity3.setArtist(artist4);
        DVDEntity dvdEntity3 = new DVDEntity();
        dvdEntity3.setName("Golden Hits Collection");
        dvdEntity3.setPrice(12);
        dvdEntity3.setDiscountRate(0.1);
        dvdEntity3.setGenre(EGenre.COUNTRY);
        dvdEntity3.setQuality(EDVDQuality.MEDIUM);
        dvdEntity3.setArtist(artist4);

        ArtistEntity artist5 = new ArtistEntity();
        artist5.setFirstName("Sam");
        artist5.setLastName("Dees");
        artist5.setDescription(" American soul singer, songwriter and record producer.");
        CDEntity cdEntity3 = new CDEntity();
        cdEntity3.setName("The Show Must Go On");
        cdEntity3.setPrice(50);
        cdEntity3.setDiscountRate(0.25);
        cdEntity3.setGenre(EGenre.SOUL);
        cdEntity3.setArtist(artist5);

        ArtistEntity artist6 = new ArtistEntity();
        artist6.setFirstName("Eric");
        artist6.setLastName("Clapton");
        artist6.setDescription("English rock and blues guitarist, singer, and songwriter,");
        CDEntity cdEntity4 = new CDEntity();
        cdEntity4.setName("Reptile");
        cdEntity4.setPrice(80);
        cdEntity4.setDiscountRate(0.11);
        cdEntity4.setGenre(EGenre.BLUES);
        cdEntity4.setArtist(artist6);
        DVDEntity dvdEntity4 = new DVDEntity();
        dvdEntity4.setName("Me and Mr. Johnson");
        dvdEntity4.setPrice(120);
        dvdEntity4.setGenre(EGenre.BLUES);
        dvdEntity4.setQuality(EDVDQuality.MEDIUM);
        dvdEntity4.setArtist(artist6);




        ArtistController artistController = new ArtistController();
        CDController cdController = new CDController();
        DVDController dvdController = new DVDController();
        VinylController vinylController = new VinylController();

        artistController.create(artist1);
        cdController.create(cdEntity1);
        cdController.create(cdEntity2);

        artistController.create(artist2);
        dvdController.create(dvdEntity1);
        dvdController.create(dvdEntity2);

        artistController.create(artist3);
        vinylController.create(vinylEntity1);
        vinylController.create(vinylEntity2);

        artistController.create(artist4);
        vinylController.create(vinylEntity3);
        dvdController.create(dvdEntity3);

        artistController.create(artist5);
        cdController.create(cdEntity3);

        artistController.create(artist6);
        cdController.create(cdEntity4);
        dvdController.create(dvdEntity4);




    }
}


