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
import com.bilgeadam.marathon.model.CD;
import com.bilgeadam.marathon.util.McUtils;

public class AdminPanel {

    public void showAdminPanel() {
        System.out.println();
        int choice = 0;
        do {
            choice = McUtils.readInt("Lütfen ne yapmak istediğinizi seçiniz:\n" + "\t1) Albüm oluştur\n"
                    + "\t2) Albüm güncelle\n" + "\t3) Albüm sil\n" + "\t99) Exit\n");
            switch (choice) {
                case 1:
                    createAlbum();
                    break;
                case 2:
                    updateAlbum();
                    System.out.println("Yapım aşamasında...");
                    break;
                case 3:
                    System.out.println("Yapım aşamasında...");
                    break;

                case 99:
                    System.exit(-1);
                    break;

            }

        } while (true);
    }

    public void createAlbum() {
        System.out.println();
        int choice = 0;
        ArtistEntity artist = new ArtistEntity();
        ArtistController artistController = new ArtistController();
        choice = McUtils.readInt("Oluşturmak istediğiniz albümün tipi:\n" + "\t1) CD\n"
                + "\t2) DVD\n" + "\t3) Vinyl\n" + "\t99) Exit\n");
        switch (choice) {
            case 1:
                CDEntity cdEntity = new CDEntity();
                CDController cdController = new CDController();
                cdEntity.setName(McUtils.readString("Albümün ismi"));
                cdEntity.setPrice(McUtils.readDouble("Albümün fiyatı"));
                String isDiscountCD = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountCD.equalsIgnoreCase("Y")) {
                    cdEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    cdEntity.setDiscountRate(1);
                }
                setAlbumGenres(cdEntity);
                artist.setFirstName(McUtils.readString("Artistin adı"));
                artist.setLastName(McUtils.readString("Artistin soyadı"));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi"));
                cdEntity.setArtist(artist);
                artist.addCD(cdEntity);

                artistController.create(artist);
                cdController.create(cdEntity);
                break;
            case 2:
                DVDEntity dvdEntity = new DVDEntity();
                DVDController dvdController = new DVDController();
                dvdEntity.setName(McUtils.readString("Albümün ismi: "));
                dvdEntity.setPrice(McUtils.readDouble("Albümün fiyatı: "));
                String isDiscountDVD = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountDVD.equalsIgnoreCase("Y")) {
                    dvdEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    dvdEntity.setDiscountRate(1);
                }
                setAlbumGenres(dvdEntity);
                setAlbumQuality(dvdEntity);
                artist.setFirstName(McUtils.readString("Artistin adı: "));
                artist.setLastName(McUtils.readString("Artistin soyadı: "));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi: "));
                dvdEntity.setArtist(artist);
                artist.addDVD(dvdEntity);

                artistController.create(artist);
                dvdController.create(dvdEntity);
                break;
            case 3:
                VinylEntity vinylEntity = new VinylEntity();
                VinylController vinylController = new VinylController();
                vinylEntity.setName(McUtils.readString("Albümün ismi: "));
                vinylEntity.setPrice(McUtils.readDouble("Albümün fiyatı: "));
                String isDiscountVinyl = McUtils.readString("İndirim uygulanacak mı? (Y,N)");
                if (isDiscountVinyl.equalsIgnoreCase("Y")) {
                    vinylEntity.setDiscountRate(McUtils.readDouble("İndirim oranı (0 ile 1 arasında verilmelidir (Ex. 0.2)): "));
                } else {
                    vinylEntity.setDiscountRate(1);
                }
                setAlbumGenres(vinylEntity);
                setVinylDiameter(vinylEntity);
                setVinylSpeed(vinylEntity);
                artist.setFirstName(McUtils.readString("Artistin adı: "));
                artist.setLastName(McUtils.readString("Artistin soyadı: "));
                artist.setDescription(McUtils.readString("Artistin kısa biyografisi: "));
                vinylEntity.setArtist(artist);
                artist.addVinyl(vinylEntity);

                artistController.create(artist);
                vinylController.create(vinylEntity);
                break;
            case 99:
                System.exit(-1);
                break;
        }
    }

    public void updateAlbum() {
        /*System.out.println();
        int choice = 0;
        ArtistEntity artist = new ArtistEntity();
        ArtistController artistController = new ArtistController();

        choice = McUtils.readInt("Güncellemek istediğiniz albümün tipi:\n" + "\t1) CD\n"
                + "\t2) DVD\n" + "\t3) Vinyl\n" + "\t99) Exit\n");

        switch (choice) {
            case 1:
                CDEntity entity = new CDEntity();
                CDController controller = new CDController();
                controller.update(entity);
                break;
        }*/
    }

    private void setVinylSpeed(VinylEntity vinylEntity) {
        int qualityChoice = (McUtils.readInt("Plağın hızı nedir?\n " +
                "1) LOW\" \n" +
                " 2) MEDIUM\"\n" +
                " 3) HIGH\"\n"));

        switch (qualityChoice) {
            case 1:
                vinylEntity.setSpeed(EVinylRPM.LOW);
                break;
            case 2:
                vinylEntity.setSpeed(EVinylRPM.MEDIUM);;
                break;
            case 3:
                vinylEntity.setSpeed(EVinylRPM.HIGH);
                break;
        }
    }

    private void setVinylDiameter(VinylEntity vinylEntity) {
        int qualityChoice = (McUtils.readInt("Plağın boyutu nedir?\n " +
                "1) 7\" \n" +
                " 2) 10\"\n" +
                " 3) 12\"\n"));

        switch (qualityChoice) {
            case 1:
                vinylEntity.setDiameter(EVinlyDiameter.SEVEN);
                break;
            case 2:
                vinylEntity.setDiameter(EVinlyDiameter.TEN);
                break;
            case 3:
                vinylEntity.setDiameter(EVinlyDiameter.TWELVE);
                break;
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
            case 1:
                cdEntity.setGenre(EGenre.BLUES);
                break;
            case 2:
                cdEntity.setGenre(EGenre.JAZZ);
                break;
            case 3:
                cdEntity.setGenre(EGenre.ROCK);
                break;
            case 4:
                cdEntity.setGenre(EGenre.COUNTRY);
                break;
            case 5:
                cdEntity.setGenre(EGenre.DANCE);
                break;
            case 6:
                cdEntity.setGenre(EGenre.HIPHOP);
                break;
            case 7:
                cdEntity.setGenre(EGenre.SOUL);
                break;
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
            case 1:
                dvdEntity.setGenre(EGenre.BLUES);
                break;
            case 2:
                dvdEntity.setGenre(EGenre.JAZZ);
                break;
            case 3:
                dvdEntity.setGenre(EGenre.ROCK);
                break;
            case 4:
                dvdEntity.setGenre(EGenre.COUNTRY);
                break;
            case 5:
                dvdEntity.setGenre(EGenre.DANCE);
                break;
            case 6:
                dvdEntity.setGenre(EGenre.HIPHOP);
                break;
            case 7:
                dvdEntity.setGenre(EGenre.SOUL);
                break;
        }
    }

    private void setAlbumQuality(DVDEntity dvdEntity) {
        int qualityChoice = (McUtils.readInt("Albümün kalitesi nedir? \n" +
                "1) LOW \n" +
                " 2) MEDIUM\n" +
                " 3) HIGH\n"));

        switch (qualityChoice) {
            case 1:
                dvdEntity.setQuality(EDVDQuality.LOW);
                break;
            case 2:
                dvdEntity.setQuality(EDVDQuality.MEDIUM);
                break;
            case 3:
                dvdEntity.setQuality(EDVDQuality.HIGH);
                break;
        }
    }

    public void setAlbumGenres (VinylEntity vinylEntity){

            int genreChoice = (McUtils.readInt("Albümün tarzı nedir?\n " +
                    "1) Blues \n" +
                    " 2) Jazz\n" +
                    " 3) Rock\n" +
                    " 4) Country\n" +
                    " 5) Dance\n" +
                    " 6) Hip Hop\n" +
                    "7) Soul\n"));
            switch (genreChoice) {
                case 1:
                    vinylEntity.setGenre(EGenre.BLUES);
                    break;
                case 2:
                    vinylEntity.setGenre(EGenre.JAZZ);
                    break;
                case 3:
                    vinylEntity.setGenre(EGenre.ROCK);
                    break;
                case 4:
                    vinylEntity.setGenre(EGenre.COUNTRY);
                    break;
                case 5:
                    vinylEntity.setGenre(EGenre.DANCE);
                    break;
                case 6:
                    vinylEntity.setGenre(EGenre.HIPHOP);
                    break;
                case 7:
                    vinylEntity.setGenre(EGenre.SOUL);
                    break;
            }
        }
}


