package karim.gabbasov.data

import karim.gabbasov.data.repository.mapper.ProductsDtoMapper
import karim.gabbasov.network.model.FlashSaleDto
import karim.gabbasov.network.model.LatestDto
import karim.gabbasov.network.model.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsDtoMapperTest {
    private lateinit var map: ProductsDtoMapper

    @Before
    fun setup() {
        map = ProductsDtoMapper()
    }

    @Test
    fun `mapToCatalogEntity converts latestDto and flashSaleDto to CatalogEntity`() {
        val delta = 0.0
        val latestDto = LatestDto(
            latest = listOf(
                ProductDto(
                    category = "Phones",
                    name = "Samsung S10",
                    price = 1000.0,
                    discount = null,
                    imageUrl = "url/1"
                )
            )
        )
        val flashSaleDto = FlashSaleDto(
            discountedProducts = listOf(
                ProductDto(
                    category = "Kids",
                    name = "New Balance Sneakers",
                    price = 22.5,
                    discount = 30,
                    imageUrl = "url/2"
                )
            )
        )

        val output = map.mapToCatalogEntity(
            latestDto = latestDto,
            flashSaleDto = flashSaleDto
        )

        assertEquals(
            latestDto.latest.first().category,
            output.latestProducts.first().category
        )
        assertEquals(
            latestDto.latest.first().name,
            output.latestProducts.first().name
        )
        assertEquals(
            latestDto.latest.first().price,
            output.latestProducts.first().price,
            delta
        )
        assertEquals(
            latestDto.latest.first().discount,
            output.latestProducts.first().discount
        )
        assertEquals(
            latestDto.latest.first().imageUrl,
            output.latestProducts.first().imageUrl
        )

        assertEquals(
            flashSaleDto.discountedProducts.first().category,
            output.discountedProducts.first().category
        )
        assertEquals(
            flashSaleDto.discountedProducts.first().name,
            output.discountedProducts.first().name
        )
        assertEquals(
            flashSaleDto.discountedProducts.first().price,
            output.discountedProducts.first().price,
            delta
        )
        assertEquals(
            flashSaleDto.discountedProducts.first().discount,
            output.discountedProducts.first().discount
        )
        assertEquals(
            flashSaleDto.discountedProducts.first().imageUrl,
            output.discountedProducts.first().imageUrl
        )
    }
}
