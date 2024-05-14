package com.grinder.service.implement;


import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import com.grinder.exception.FileRoadFailedException;
import com.grinder.repository.ImageRepository;
import com.grinder.service.AwsS3Service;
import com.grinder.utils.AwsProperties;
import com.grinder.utils.FileNameUtils;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private final AwsProperties awsProperties;

    private final ImageRepository imageRepository;

    private AmazonS3 s3Client;


    @PostConstruct
    private void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getAccessKey(),
                awsProperties.getSecretKey());

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsProperties.getStatics())
                .build();
    }


    //awsProperties 객체에 있는 bucket 정보를 파일과 함께 매개변수에 담아 실제 업로드 로직이 있는 upload() 메서드를 호출 및 return
    @Override
    @Transactional
    public boolean uploadFileBucket(List<MultipartFile>fileList, String contentId,ContentType contentType) {
        // TODO : 파일 기능 필요시 파일 class 생성 후 보관
        for(MultipartFile file : fileList){
            if(file != null){
                String imageURL = uploadFile(file, awsProperties.getBucket());
                Image image = Image.builder()
                        .imageUrl(imageURL)
                        .contentId(contentId)
                        .contentType(contentType)
                        .build();
                imageRepository.save(image);
            }
        }
        return true;
    }
    @Override
    public boolean uploadImageBucket(List<MultipartFile> imgList, String contentId, ContentType contentType){
        for(MultipartFile img : imgList){
            if(img != null){
                String imageURL = uploadImageFile(img, awsProperties.getBucket());
                Image image = Image.builder()
                        .imageUrl(imageURL)
                        .contentId(contentId)
                        .contentType(contentType)
                        .build();
                imageRepository.save(image);
            }
        }
        return true;
    }
    public String uploadFile(MultipartFile file, String bucket) {
        String fileName = file.getOriginalFilename();
        String convertedFileName = FileNameUtils.fileNameConvert(fileName);

        try {
            String mimeType = new Tika().detect(file.getInputStream());
            ObjectMetadata metadata = new ObjectMetadata();

            FileNameUtils.checkMimeType(mimeType);
            metadata.setContentType(mimeType);
            s3Client.putObject(
                    new PutObjectRequest(bucket, convertedFileName, file.getInputStream(), metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException exception) {
            throw new FileRoadFailedException("파일 저장에 실패하였습니다.");
        }

        return s3Client.getUrl(bucket, convertedFileName).toString();
    }
    public String uploadImageFile(MultipartFile file, String bucket) {
        String fileName = file.getOriginalFilename();
        String convertedFileName = FileNameUtils.fileNameConvert(fileName);

        try {
            String mimeType = new Tika().detect(file.getInputStream());
            ObjectMetadata metadata = new ObjectMetadata();

            FileNameUtils.checkImageMimeType(mimeType);
            metadata.setContentType(mimeType);
            s3Client.putObject(
                    new PutObjectRequest(bucket, convertedFileName, file.getInputStream(), metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException exception) {
            throw new FileRoadFailedException("이미지 파일 저장에 실패하였습니다.");
        }

        return s3Client.getUrl(bucket, convertedFileName).toString();
    }
    // Todo :  파일 삭제


    //파일 다운로드 -> TODO : 필요 없으면 삭제
    public byte[] downloadFile(String file) {

        String filename = file.substring(file.lastIndexOf('/') + 1);

        S3Object s3Object = s3Client.getObject(awsProperties.getBucket(), filename);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Image uploadSingleImageBucket(MultipartFile imageFile, String contentId, ContentType contentType){
        String imageURL = uploadImageFile(imageFile, awsProperties.getBucket());
        Image image = Image.builder()
                .imageUrl(imageURL)
                .contentId(contentId)
                .contentType(contentType)
                .build();
        return imageRepository.save(image);
    }

    @Override
    public void deleteFile(String fileURL) throws IOException {
        try{
            s3Client.deleteObject(awsProperties.getBucket(),fileURL);
        }catch (SdkClientException e){
            throw new IOException("Error deleting file from S3");
        }
    }
}