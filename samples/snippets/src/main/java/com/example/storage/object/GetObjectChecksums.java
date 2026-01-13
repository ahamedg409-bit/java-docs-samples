package com.example.storage.object;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.ChecksumRequestParams;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;

public class GetObjectChecksums {
  public static void getObjectChecksums(String bucketName, String objectName) throws IOException {
    // [START storage_get_object_checksums]
    // The ID of your GCS bucket
    // String bucketName = "your-bucket-name";
    // The ID of your GCS object
    // String objectName = "your-object-name";

    Storage storage = StorageOptions.getDefaultInstance().getService();

    ChecksumRequestParams checksumRequestParams =
        Storage.ChecksumRequestParams.newBuilder().setChecksumAlgorithm(Storage.ChecksumAlgorithm.CRC32C, Storage.ChecksumAlgorithm.MD5).build();

    BlobInfo blobInfo =
        storage.get(BlobId.of(bucketName, objectName), Storage.BlobGetOption.checksumRequestParams(checksumRequestParams));

    if (blobInfo == null) {
      System.out.println("\nThere is no such object!\n");
      return;
    }

    System.out.println("Object: " + objectName + " in bucket: " + bucketName + " has CRC32C: " + blobInfo.getCrc32c() + " and MD5: " + blobInfo.getMd5());
    // [END storage_get_object_checksums]
  }
}
