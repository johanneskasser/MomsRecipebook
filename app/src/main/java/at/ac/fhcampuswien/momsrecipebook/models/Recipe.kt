package at.ac.fhcampuswien.momsrecipebook.models

data class Ingredient(
    val quantity: Int,
    val unit: String,
    val name: String
)

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val author: String,
    val images: List<String>,
    val ingredients: List<Ingredient>
)

fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id= "1",
            title="Gulasch",
            description = "Gulasch ist ein Ragout ungarischen Ursprungs. Was in allen anderen Ländern „Gulasch“, „Gulyas“ oder „goulash“ heißt, ist jedoch in Ungarn das Pörkölt bzw. Paprikás. Nur in Ungarn ist ein gulyás eine Suppe.",
            time = "2.0",
            author = "Johannes",
            images = listOf(
                "https://image.essen-und-trinken.de/11938360/t/fw/v10/w1440/r1/-/klassisches-gulasch-65c26540e76b821bff1211bb12590705-fjt20161203801-jpg--57833-.jpg",
                "https://image.essen-und-trinken.de/11953860/t/lD/v10/w1440/r1/-/wiener-gulasch-jpg--65879-.jpg",
                "https://images.eatsmarter.de/sites/default/files/styles/1600x1200/public/gulasch-mit-paprika-und-petersilie-60973.jpg"
            ),
            ingredients = listOf(
                Ingredient(quantity = 1, unit = "kg", name = "Gulaschfleisch im Ganzen vom Rind"),
                Ingredient(quantity = 1, unit = "stk", name = "Zwiebeln"),
                Ingredient(quantity = 4, unit = "ml", name = "Öl zum Anrösten"),
                Ingredient(quantity = 2, unit = "g", name = "Paprikapulver (edelsüß)")
            )
        ),
        Recipe(
            id= "2",
            title="Erdbeer Tiramisu",
            description = "Dieses Erdbeer Tiramisu wird Sie aufgrund seiner Fruchtigkeit und seines einfachen Rezeptes begeistern.",
            time = "6.6",
            author = "Johannes",
            images = listOf(
                "https://www.gutekueche.at/storage/media/recipe/11379/resp/erdbeer-tiramisu___webp_940_625.webp",
                "https://www.gutekueche.at/storage/media/recipe/11380/resp/erdbeer-tiramisu_1468932353___webp_620_465.webp",
                "https://www.gutekueche.at/storage/media/recipe/11381/resp/erdbeer-tiramisu_1469524254___webp_620_465.webp"
            ),
            ingredients = listOf(
                Ingredient(quantity = 1, unit = "kg", name = "Erdbeeren"),
                Ingredient(quantity = 750, unit = "g", name = "Mascarpone"),
                Ingredient(quantity = 500, unit = "g", name = "Magertopfen"),
                Ingredient(quantity = 2, unit = "EL", name = "Zitronensaft")
            )
        ),
        Recipe(
            id= "3",
            title="Pulled Pork",
            description = "Pulled Pork - der Klassiker aus den USA ist jetzt auch bei uns angekommen – so gelingt ein perfektes Pulled Pork zu Hause am Grill oder im Ofen.",
            time = "9.9",
            author = "Johannes",
            images = listOf(
                "https://www.gutekueche.at/storage/media/recipe/39620/resp/pulled-pork___webp_620_414.webp",
                "https://img.chefkoch-cdn.de/rezepte/3925141599811637/bilder/1335160/crop-960x720/pulled-pork-aus-dem-ofen.jpg",
                "https://images.eatsmarter.de/sites/default/files/styles/max_size/public/vegane-pulled-pork-burger-639570-1.jpg"
            ),
            ingredients = listOf(
                Ingredient(quantity = 2, unit = "kg", name = "Schweinenacken od. Schweineschulter"),
                Ingredient(quantity = 6, unit = "EL", name = "Paprikapulver (edelsüß)"),
                Ingredient(quantity = 2, unit = "EL", name = "Zucker"),
                Ingredient(quantity = 2, unit = "EL", name = "Salz")
            )
        ),
        Recipe(
            id= "4",
            title=" Lachs vom Grill",
            description = "Der Lachs vom Grill ist ein köstliches Rezept auch für Kochanfänger. Einfach den Fisch mit der Marinade bestreichen und ab auf den Grill.",
            time = "9.9",
            author = "Johannes",
            images = listOf(
                "https://www.gutekueche.at/storage/media/recipe/26860/resp/lachs-vom-grill___webp_620_413.webp",
                "https://www.gutekueche.at/storage/media/recipe/26861/resp/lachs-vom-grill_1462868825___webp_620_413.webp",
                "https://www.springlane.de/magazin/wp-content/uploads/2016/08/Lachs-Grillen-How-To-6-1.jpg"
            ),
            ingredients = listOf(
                Ingredient(quantity = 4, unit = "Stk", name = "Lachssteak (mit Haut)"),
                Ingredient(quantity = 2, unit = "Stk", name = "Limetten (oder Zitronen)"),
                Ingredient(quantity = 4, unit = "EL", name = "Olivenöl"),
                Ingredient(quantity = 1, unit = "Bund", name = "Dill")
            )
        ),
    )
}