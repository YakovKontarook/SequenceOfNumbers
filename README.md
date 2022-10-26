# SequenceOfNumbers
Service for processing files consisting sequence of numbers

## Description:

The service allows you to process numeric data from a uploaded txt file. 

## Service operations:
- maximum number in the file
- minimum number in the file
- median
- arithmetic average value
- the longest sequence of consecutive numbers in ascending or descending order

## Results from Relex TestFile:

maximum number in the file =  49999978

minimum number in the file = -49999996

median = 25216.0

arithmetic average value = 7364.418442641844

the longest sequence of consecutive numbers in ascending order = 
{-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185}

the longest sequence of consecutive numbers in descending order =
{ 47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762}

## Project Features:
- The results are output in json or xml formats. The format can be changed by adding header "accept:application/xml" or "accept:application/xml"
- A file can be passed in binary form in a post request
- All operations with files are cached, which speeds up the output

## User's guide:
# 1) Upload a file you want to be analized. Numbers in the file should be located line by line. 

![upload](https://user-images.githubusercontent.com/88117408/198003369-c9c10496-413d-4a39-a601-9850f16f1eef.jpg)

### Response 

![UploadResponsePNG](https://user-images.githubusercontent.com/88117408/198004496-48233787-800e-4d9d-b70e-c57ddcf54e7a.png)

# 2) Select the operation you want to perform:
# - maximum number in the file

![MaxValuePNG](https://user-images.githubusercontent.com/88117408/198006442-8f8cd10b-be1f-4c1b-861a-d952512e6818.png)

### Response

![MaxValueResponsePNG](https://user-images.githubusercontent.com/88117408/198006755-966ec49b-0946-4ae9-9021-580e7aefbed8.png)

# - minimum number in the file

![MinValuePNG](https://user-images.githubusercontent.com/88117408/198009892-4dca1e90-0521-4c84-b02d-e282e1ecc30d.png)

### Response

![MinValueResponsePNG](https://user-images.githubusercontent.com/88117408/198009948-aa6d986d-2e01-40f3-90fc-980586aefb77.png)

# - median

![MedianPNG](https://user-images.githubusercontent.com/88117408/198010036-3825957f-6433-4ac6-b370-4a198147e42d.png)

### Response

![MedianResponsePNG](https://user-images.githubusercontent.com/88117408/198010112-b9fdfc35-28de-4d71-a4a6-1ffdcffda588.png)

# - arithmetic average value

![AveragePNG](https://user-images.githubusercontent.com/88117408/198010178-e614603a-d4ad-47f7-91bc-e1fc4cdedc55.png)

### Response

![AverageResponsePNG](https://user-images.githubusercontent.com/88117408/198010219-51b55eba-f9c5-4f17-8230-2b477b816f8d.png)

# - the longest sequence of consecutive numbers in ascending or descending order

![LongestSeqPNG](https://user-images.githubusercontent.com/88117408/198010311-bc8e01a5-6d7b-49d6-b109-b453f2e849cf.png)

### Response in ascending oreder

![AscResponsePNG](https://user-images.githubusercontent.com/88117408/198010396-b9cf076b-d6c9-4110-928e-2c77b7b31db7.png)

### Response in descending oreder

![DescResponsePNG](https://user-images.githubusercontent.com/88117408/198010469-d68623c4-b2f3-456d-8d04-bbfc342454d7.png)
