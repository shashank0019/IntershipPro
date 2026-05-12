package com.karunadavanya.data.repository

import android.content.Context
import com.karunadavanya.data.model.WildlifeAnimal
import com.karunadavanya.utils.JsonAssetLoader

class EducationRepository {
    fun getWildlifeAnimals(context: Context): List<WildlifeAnimal> {
        val animals = JsonAssetLoader.loadWildlifeAnimals(context)
        return if (animals.isNotEmpty()) animals else defaultAnimals()
    }

    private fun defaultAnimals(): List<WildlifeAnimal> = listOf(

        WildlifeAnimal(
            name        = "Tiger",
            description = "The Bengal Tiger is India's apex predator — a solitary, powerful carnivore and a nationally protected species.",
            habitat     = "Dense forests, tall grasslands",
            behavior    = "Solitary, mostly active at dawn and dusk. Expert ambush hunter.",
            safetyTips  = "Do not run. Make yourself visible and back away slowly.",
            imageName   = "animal_tiger",
            tips = listOf(
                "Do NOT run — running triggers the predator chase instinct.",
                "Stand tall, make eye contact and back away slowly and calmly.",
                "If in a group, stay together and appear as one large entity.",
                "Report any sighting to the forest department within 24 hours."
            )
        ),

        WildlifeAnimal(
            name        = "Elephant",
            description = "Indian Elephants are the largest land animals in Asia — intelligent, social, and deeply family-oriented.",
            habitat     = "Forest edges and water-rich habitats",
            behavior    = "Herd animals, usually peaceful but defensive when calves are near.",
            safetyTips  = "Give space and never block the path of the herd.",
            imageName   = "animal_elephant",
            tips = listOf(
                "Stay calm and move slowly away — never shout or run.",
                "Never position yourself between a mother and her calf.",
                "If in a vehicle, switch off the engine and remain inside.",
                "Mock charges are common — wait patiently until the elephant settles."
            )
        ),

        WildlifeAnimal(
            name        = "Leopard",
            description = "The Indian Leopard is a stealthy, adaptable predator that can thrive close to human settlements.",
            habitat     = "Rocky hills, forest patches, sugarcane fields",
            behavior    = "Nocturnal, highly alert, solitary. Excellent climber.",
            safetyTips  = "Do not approach. Keep children indoors at dusk and dawn.",
            imageName   = "animal_leopard",
            tips = listOf(
                "Never walk alone near forest edges or fields after dark.",
                "Do NOT crouch or sit — it makes you appear smaller, like prey.",
                "Secure livestock, poultry, and garbage inside after sunset.",
                "Install motion-sensor lights around homes near forest edges."
            )
        ),

        WildlifeAnimal(
            name        = "Sloth Bear",
            description = "The Indian Sloth Bear is a shaggy, insect-eating bear — short-sighted and known to charge when startled.",
            habitat     = "Dense forests, rocky outcrops, hilly regions",
            behavior    = "Mostly nocturnal, forages for termites, honeycombs, and fruit.",
            safetyTips  = "Make noise while walking to avoid surprise encounters.",
            imageName   = "animal_bear",
            tips = listOf(
                "Make noise while walking in bear territory to avoid surprises.",
                "Do NOT play dead with a sloth bear — it won't help.",
                "If attacked, protect your face and neck with your arms.",
                "Avoid Mahua groves in spring — fermenting flowers attract bears."
            )
        ),

        WildlifeAnimal(
            name        = "Gaur (Indian Bison)",
            description = "The Gaur is the world's largest wild cattle species — massive, muscular, and capable of explosive speed.",
            habitat     = "Mixed forests, grassy clearings near water",
            behavior    = "Herd animal, generally shy but dangerous if provoked.",
            safetyTips  = "Give wide berth on forest roads. Never honk at them.",
            imageName   = "animal_gaur",
            tips = listOf(
                "Never honk or accelerate towards a Gaur on a forest road.",
                "Switch headlights to low beam at night to avoid startling them.",
                "If a Gaur enters farmland, call the forest department — don't chase.",
                "Herds with calves are especially defensive — keep extreme distance."
            )
        ),

        WildlifeAnimal(
            name        = "Wild Boar",
            description = "Wild Boars are aggressive, fast, and equipped with razor-sharp tusks capable of serious injury.",
            habitat     = "Forests, agricultural fields, scrublands",
            behavior    = "Move in groups (sounders), most active at dawn and dusk.",
            safetyTips  = "If charged, run to the nearest tree and climb it immediately.",
            imageName   = "animal_boar",
            tips = listOf(
                "If a boar charges, run to the nearest tree and climb it at once.",
                "Never corner a wild boar — a cornered boar always attacks.",
                "Fence root-crop and vegetable fields to prevent raiding conflicts.",
                "Never approach a piglet — the mother will charge immediately."
            )
        ),

        WildlifeAnimal(
            name        = "Mugger Crocodile",
            description = "The Mugger Crocodile inhabits freshwater rivers and lakes and is deceptively fast both in water and on land.",
            habitat     = "Rivers, lakes, reservoirs, wetlands",
            behavior    = "Ambush predator. Basks on banks; most active at dusk and dawn.",
            safetyTips  = "Stay 5 metres from water edges in crocodile habitats.",
            imageName   = "animal_crocodile",
            tips = listOf(
                "Never swim or wade in rivers near wildlife reserve areas.",
                "Stay at least 5 metres from the water's edge at all times.",
                "Crocodiles can outrun a human over short distances on land.",
                "Most active at dawn and dusk — extra caution during those hours."
            )
        ),

        WildlifeAnimal(
            name        = "King Cobra",
            description = "The King Cobra is the world's longest venomous snake — intelligent, fast-moving, and capable of standing tall.",
            habitat     = "Dense forests, bamboo thickets, near water",
            behavior    = "Diurnal, shy but highly defensive when cornered.",
            safetyTips  = "Never attempt to handle or capture a snake of any kind.",
            imageName   = "animal_snake",
            tips = listOf(
                "Wear boots and use a stick in tall grass — most bites are accidental.",
                "Never attempt to handle, catch, or kill any snake.",
                "If bitten: immobilise the limb and go to hospital IMMEDIATELY.",
                "Do NOT cut the wound or apply tourniquet — rush to anti-venom."
            )
        ),

        WildlifeAnimal(
            name        = "Indian Wolf",
            description = "The Indian Grey Wolf is a lean, pack-hunting predator found in dry grasslands and scrublands.",
            habitat     = "Dry grasslands, scrublands, semi-arid plains",
            behavior    = "Pack hunters, mostly nocturnal. Livestock and children at risk.",
            safetyTips  = "Secure livestock inside solid enclosures every single night.",
            imageName   = "animal_wolf",
            tips = listOf(
                "Stand tall and make yourself look large — do not run.",
                "Livestock must be penned inside solid enclosures every night.",
                "Wolves operate in packs — one sighting means others are close.",
                "Report wolf presence near settlements to the forest department."
            )
        ),

        WildlifeAnimal(
            name        = "Spotted Deer (Chital)",
            description = "The graceful Chital is India's most common deer — a key prey species and alarm caller of the forest.",
            habitat     = "Forest clearings, grasslands, river banks",
            behavior    = "Timid and alert. Stags become aggressive during rutting season.",
            safetyTips  = "Observe quietly and never corner or approach a Chital.",
            imageName   = "animal_deer",
            tips = listOf(
                "Male stags are aggressive during rutting — keep safe distance.",
                "Never touch a spotted fawn — the mother is always watching.",
                "Deer crossing roads cause accidents — slow down in forest zones.",
                "Deer herds near villages indicate nearby big predators too."
            )
        ),

        WildlifeAnimal(
            name        = "Indian Peacock",
            description = "The Indian Peacock, India's national bird, is famous for its spectacular iridescent tail display.",
            habitat     = "Open forests, farmlands, scrublands",
            behavior    = "Males display during monsoon mating season; can be territorial.",
            safetyTips  = "Avoid nesting sites. Keep small pets indoors near nesting areas.",
            imageName   = "animal_peacock",
            tips = listOf(
                "Male peacocks become territorial during mating — give them space.",
                "Peacock spurs can cause deep puncture wounds — do not approach.",
                "Never disturb a nesting site — parent birds defend aggressively.",
                "Wild peafowl crossing roads are protected — always stop and wait."
            )
        ),

        WildlifeAnimal(
            name        = "Rhesus Macaque",
            description = "Rhesus Macaques are intelligent, opportunistic monkeys that adapt easily to human environments.",
            habitat     = "Forests, temples, urban fringes, agricultural areas",
            behavior    = "Troop-living, bold, and highly social. Males can be aggressive.",
            safetyTips  = "Never feed monkeys — it increases aggression and dependency.",
            imageName   = "animal_monkey",
            tips = listOf(
                "Never feed monkeys — it conditions dangerous, aggressive behaviour.",
                "Keep bags closed and food hidden — monkeys target visible food.",
                "If surrounded by a troop, stay calm and move to a building.",
                "Children are targeted more often — keep them close in monkey areas."
            )
        )
    )
}
