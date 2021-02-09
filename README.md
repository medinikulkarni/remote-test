### Requirements
**Java** - This project has been configured to use Java 11. 
**Gradle** - You do not need to install gradle. The project will be using the gradle wrapper, as long as you use the command `./gradlew`.  

### Running
To run the application via the command line - `./gradlew bootRun`

To build the application via the command line - `./gradlew build`

To test the application via the command line - `./gradlew test`

## Technologies
* Spring Boot 2
* JUnit 5
* Gradle 6

## Things wanted to do but could not complete within time limit
Resilience
Contract Driven Tests
Integration Tests

## Endpoint
`GET request - http://localhost:8080/product/search?labelType=SHOW_WAS_THEN_NOW`
default labelType = SHOW_WAS_NOW

OUTPUT SAMPLE -
`{
     "products": [
         {
             "productId": "4870146",
             "title": "Hobbs Carly Dress, Peony Pink",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFC0CB",
                     "skuId": "238481586"
                 }
             ],
             "nowPrice": "£65",
             "priceLabel": "Was £169, now £65",
             "priceReduction": 104.00
         },
         {
             "productId": "4883159",
             "title": "Hobbs Ashley Spot Jacquard Dress, Midnight",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238508231"
                 }
             ],
             "nowPrice": "£89",
             "priceLabel": "Was £179, now £89",
             "priceReduction": 90.00
         },
         {
             "productId": "4875083",
             "title": "Ghost Laura Shooting Star Crepe Midi Dress, Mint",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "008000",
                     "skuId": "238404174"
                 }
             ],
             "nowPrice": "£36",
             "priceLabel": "Was £120, then £84, now £36",
             "priceReduction": 84.00
         },
         {
             "productId": "4873363",
             "title": "Ghost Astrid Floral Dress, Navy Clusters",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238346446"
                 }
             ],
             "nowPrice": "£74",
             "priceLabel": "Was £149, now £74",
             "priceReduction": 74.50
         },
         {
             "productId": "4823284",
             "title": "Hobbs Astraea Floral Dress, Navy/Ivory",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238448191"
                 }
             ],
             "nowPrice": "£99",
             "priceLabel": "Was £169, now £99",
             "priceReduction": 70.00
         },
         {
             "productId": "4973115",
             "title": "Whistles Trailing Daisy Mini Dress, Yellow",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFFF00",
                     "skuId": "238626560"
                 }
             ],
             "nowPrice": "£79",
             "priceLabel": "Was £139, now £79",
             "priceReduction": 60.00
         },
         {
             "productId": "3935527",
             "title": "Damsel in a Dress Lanie Military Dress, Orange",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFA500",
                     "skuId": "238246104"
                 }
             ],
             "nowPrice": "£118",
             "priceLabel": "Was £169, now £118",
             "priceReduction": 50.70
         },
         {
             "productId": "4810678",
             "title": "Hobbs Suri Dress",
             "colorSwatches": [
                 {
                     "color": "Midnight",
                     "rgbColor": "0000FF",
                     "skuId": "238532883"
                 }
             ],
             "nowPrice": "£89",
             "priceLabel": "Was £139, now £89",
             "priceReduction": 50.00
         },
         {
             "productId": "4959295",
             "title": "Hobbs April Wrap Dress, Multi",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFFFFF",
                     "skuId": "238664203"
                 }
             ],
             "nowPrice": "£49",
             "priceLabel": "Was £99, now £49",
             "priceReduction": 50.00
         },
         {
             "productId": "4973251",
             "title": "Whistles Georgina Zip A-Line Dress, Pale Blue",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238888687"
                 }
             ],
             "nowPrice": "£85",
             "priceLabel": "Was £129, now £85",
             "priceReduction": 44.00
         },
         {
             "productId": "4957430",
             "title": "Whistles Alba Shift Dress, Green",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "008000",
                     "skuId": "238653932"
                 }
             ],
             "nowPrice": "£59",
             "priceLabel": "Was £99, now £59",
             "priceReduction": 40.00
         },
         {
             "productId": "1889474",
             "title": "Hobbs Linen Twitchill Dress, Navy/Red",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238516984"
                 }
             ],
             "nowPrice": "£89",
             "priceLabel": "Was £129, now £89",
             "priceReduction": 40.00
         },
         {
             "productId": "4868356",
             "title": "AND/OR Sofia Check Print Dress, Black/White",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "000000",
                     "skuId": "238329616"
                 }
             ],
             "nowPrice": "£53",
             "priceLabel": "Was £89, now £53",
             "priceReduction": 36.00
         },
         {
             "productId": "4892111",
             "title": "Whistles Enora Pocketed Dress",
             "colorSwatches": [
                 {
                     "color": "Khaki",
                     "rgbColor": "008000",
                     "skuId": "239066752"
                 },
                 {
                     "color": "Burgundy",
                     "rgbColor": "FF0000",
                     "skuId": "239066738"
                 }
             ],
             "nowPrice": "£65",
             "priceLabel": "Was £99, now £65",
             "priceReduction": 34.00
         },
         {
             "productId": "4907666",
             "title": "Boden Cecily Mini Dress, Soft Stone",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFFFFF",
                     "skuId": "238520948"
                 }
             ],
             "nowPrice": "£51",
             "priceLabel": "Was £85, now £51",
             "priceReduction": 34.00
         },
         {
             "productId": "4955100",
             "title": "Hobbs Lizzie Stripe Flared Dress, Black/Ivory",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "000000",
                     "skuId": "238627913"
                 }
             ],
             "nowPrice": "£65",
             "priceLabel": "Was £99, now £65",
             "priceReduction": 34.00
         },
         {
             "productId": "5223730",
             "title": "Whistles Midi Length Knit Dress",
             "colorSwatches": [
                 {
                     "color": "Navy",
                     "rgbColor": "0000FF",
                     "skuId": "239076673"
                 }
             ],
             "nowPrice": "£95",
             "priceLabel": "Was £129, now £95",
             "priceReduction": 34.00
         },
         {
             "productId": "4827086",
             "title": "hush Iris T-Shirt Dress, Grey Snake Print",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "FFFFFF",
                     "skuId": "238494053"
                 }
             ],
             "nowPrice": "£30",
             "priceLabel": "Was £55, now £30",
             "priceReduction": 25.00
         },
         {
             "productId": "4973219",
             "title": "White Stuff Shelley Denim Pinny Dress, Blue",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238662125"
                 }
             ],
             "nowPrice": "£35",
             "priceLabel": "Was £59, now £35",
             "priceReduction": 24.95
         },
         {
             "productId": "4211083",
             "title": "hush Maple Sateen Midi Dress",
             "colorSwatches": [
                 {
                     "color": "Blue",
                     "rgbColor": "0000FF",
                     "skuId": "238536378"
                 }
             ],
             "nowPrice": "£55",
             "priceLabel": "Was £79, now £55",
             "priceReduction": 24.00
         },
         {
             "productId": "4894290",
             "title": "Jolie Moi Geometric Print Cross Over Maxi Dress",
             "colorSwatches": [
                 {
                     "color": "Pink",
                     "rgbColor": "FFC0CB",
                     "skuId": "238565979"
                 }
             ],
             "nowPrice": "£75",
             "priceLabel": "Was £95, now £75",
             "priceReduction": 20.00
         },
         {
             "productId": "4829697",
             "title": "Chi Chi London Curve Anthea Dress, Navy",
             "colorSwatches": [
                 {
                     "color": "",
                     "rgbColor": "0000FF",
                     "skuId": "238344223"
                 }
             ],
             "nowPrice": "£45",
             "priceLabel": "Was £65, now £45",
             "priceReduction": 19.50
         }
     ]
 }`
