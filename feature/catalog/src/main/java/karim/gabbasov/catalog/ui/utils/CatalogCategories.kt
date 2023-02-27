package karim.gabbasov.catalog.ui.utils

import karim.gabbasov.catalog.R

internal enum class CatalogCategories {
    PHONES {
        override val stringResId = R.string.phones_title
        override val drawableResId = R.drawable.phones
    },
    HEAD_PHONES {
        override val stringResId = R.string.headphones_title
        override val drawableResId = R.drawable.headphones
    },
    GAMES {
        override val stringResId = R.string.games_title
        override val drawableResId = R.drawable.games
    },
    CARS {
        override val stringResId = R.string.cars_title
        override val drawableResId = R.drawable.cars
    },
    FURNITURE {
        override val stringResId = R.string.furniture_title
        override val drawableResId = R.drawable.furniture
    },
    KIDS {
        override val stringResId = R.string.kids_title
        override val drawableResId = R.drawable.kids
    };

    abstract val stringResId: Int

    abstract val drawableResId: Int

    companion object {
        fun getCatalogCategories(): List<CatalogCategories> {
            return listOf(PHONES, HEAD_PHONES, GAMES, CARS, FURNITURE, KIDS)
        }
    }
}
