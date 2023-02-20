package com.projet_integre.taskflow.services;


import com.projet_integre.taskflow.entities.ImageData;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.repositories.ImageDataRepository;
import com.projet_integre.taskflow.utils.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Transactional
    public String uploadImage(MultipartFile file, Utilisateur user) throws IOException {

          Optional<ImageData> image= imageDataRepository.findByUtilisateur(user);
          if (image.isPresent()){
              imageDataRepository.delete(image.get());
          }



        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                        .utilisateur(user)
                .imageData(ImageUtil.compressImage(file.getBytes())).build());


        return ("Image uploaded successfully: " );

    }

    @Transactional
    public ImageData getInfoByImageByUser(Utilisateur user) {
        Optional<ImageData> dbImage = imageDataRepository.findByUtilisateur(user);
        if (!dbImage.isPresent()){
            return null;
        }
        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }


    @Transactional
    public byte[] getImage(Utilisateur user) {
        Optional<ImageData> dbImage = imageDataRepository.findByUtilisateur(user);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}