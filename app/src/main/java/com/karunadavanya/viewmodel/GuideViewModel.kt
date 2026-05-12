package com.karunadavanya.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karunadavanya.data.model.SafetyGuideItem

class GuideViewModel : ViewModel() {
    private val _items = MutableLiveData<List<SafetyGuideItem>>()
    val items: LiveData<List<SafetyGuideItem>> = _items

    fun loadItems() {
        _items.value = listOf(

            SafetyGuideItem(
                title     = "🐯 Tiger Sightings",
                imageName = "animal_tiger",
                iconName  = "tiger",
                details   = """
• Do NOT run — running activates the predator-chase instinct.
• Stand tall, make eye contact, and back away slowly and calmly.
• Speak in a firm voice — do not scream or panic.
• If in a group, stay together and appear as one large entity.
• Carry a whistle or bell while walking in tiger territory.
• Tiger attacks at dawn and dusk are more common — be extra cautious.
• If attacked, fight back with anything available — tigers can be deterred.
• Never approach a tiger kill — the predator will be nearby.
• Avoid wearing bright colours or strong perfumes in forests.
• Report any sighting to the forest department within 24 hours.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐘 Elephant Encounters",
                imageName = "animal_elephant",
                iconName  = "elephant",
                details   = """
• Stay calm — never shout, scream, or wave your arms.
• Move slowly and steadily away; do not turn and run.
• Never position yourself between a mother elephant and her calf.
• If in a vehicle, switch off the engine and stay inside quietly.
• Mock charges are common — wait patiently until the elephant settles.
• Elephants use infrasound; you may feel vibrations before you see them.
• If on foot, retreat behind large trees or solid cover.
• Do NOT use flash photography — it can trigger aggression.
• Report herd movement to the forest department immediately.
• Keep livestock away from forest-edge fields at night.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐆 Leopard Encounters",
                imageName = "animal_leopard",
                iconName  = "leopard",
                details   = """
• Leopards are stealthy — always be vigilant near forests and fields.
• Never walk alone near forest edges or sugarcane fields after dark.
• If you spot a leopard, maintain eye contact and back away slowly.
• Do NOT crouch or sit — it makes you appear smaller, like prey.
• Leopards are excellent climbers — do not climb trees to escape.
• Secure garbage, livestock, and poultry enclosures after dark.
• Install motion-sensor lights around homes near forest edges.
• Children and pets are especially vulnerable — keep them indoors at night.
• If a leopard attacks, protect your throat and neck with your arms.
• Loud noises (banging pots) can deter a curious leopard.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐻 Sloth Bear Encounters",
                imageName = "animal_bear",
                iconName  = "bear",
                details   = """
• Sloth bears are short-sighted and may charge if startled.
• Make noise while walking in bear territory to avoid surprise encounters.
• If you encounter one, speak calmly and back away slowly.
• Do NOT play dead with a sloth bear — unlike other bears, this does not help.
• If attacked, protect your face, head, and neck with your arms.
• Sloth bears are attracted to beehives and fruit — avoid such areas.
• They are mostly nocturnal — avoid forest trails between dusk and dawn.
• Mahua flowers attract sloth bears in spring — stay away from Mahua groves.
• Never stand between a mother and her cubs.
• Report bear presence near villages to local forest authorities.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🦬 Gaur (Indian Bison) Encounters",
                imageName = "animal_gaur",
                iconName  = "bison",
                details   = """
• Gaur are the world's largest wild cattle — do not underestimate them.
• They appear docile but can charge explosively when threatened.
• Give them wide berth on forest roads — never honk or accelerate towards them.
• If on foot, climb to higher ground or behind large trees.
• Gaur are most active early morning and late evening near water bodies.
• Herds with calves are especially dangerous — the herd will protect calves.
• Never try to photograph them from close range.
• Switch headlights to low beam when driving — bright lights can alarm them.
• If a Gaur enters your farmland, do not try to chase it away alone.
• Contact the forest department — attempting to drive them away can be fatal.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐗 Wild Boar Sightings",
                imageName = "animal_boar",
                iconName  = "boar",
                details   = """
• Wild boars are aggressive and faster than they appear.
• If a boar charges, run to the nearest tree and climb it.
• They have razor-sharp tusks that can cause serious injury.
• Never corner a wild boar — a cornered boar always attacks.
• Boars often move in groups — if you see one, expect more nearby.
• They are most active at dusk and dawn near agricultural land.
• Fence vegetable and root-crop fields to prevent raiding and confrontations.
• Dogs can trigger boar aggression — keep pets leashed near forest edges.
• Never try to rescue a piglet — the mother will charge immediately.
• Use flashlights at night in areas known for boar activity.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐊 Mugger Crocodile Encounters",
                imageName = "animal_crocodile",
                iconName  = "crocodile",
                details   = """
• Never swim or wade in rivers, lakes, or ponds near wildlife areas.
• Stay at least 5 metres from the water's edge in crocodile habitats.
• Crocodiles are deceptively fast on land — they can outrun a human.
• Be cautious when collecting water, washing, or fishing near rivers.
• If a crocodile attacks, strike its eyes and nostrils — its most sensitive areas.
• Do NOT approach a basking crocodile — it is not sleeping, it is alert.
• Avoid the water at dawn and dusk — crocodiles are most active then.
• Never leave fish scraps or food waste near water banks.
• Children should never play near riverbanks unsupervised.
• Report crocodile sightings near human settlements to the forest department.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🐍 King Cobra & Snake Safety",
                imageName = "animal_snake",
                iconName  = "snake",
                details   = """
• Most snakebites happen due to accidental stepping — wear boots in tall grass.
• Never put your hand in dark holes, crevices, or hollow logs.
• King Cobras are the world's longest venomous snake — give extreme distance.
• If you see a snake, freeze, then slowly back away without sudden movement.
• Never attempt to handle, capture, or kill a snake — most bites occur this way.
• Keep your surroundings clear of debris, firewood piles, and rat burrows.
• Use a torch at night when walking outdoors — snakes are active after rain.
• If bitten: immobilise the bitten limb and go to hospital IMMEDIATELY.
• Do NOT apply a tourniquet or cut the wound — rush to anti-venom.
• Save the National Snake Helpline: 1800-212-0005.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🦊 Indian Wolf Sightings",
                imageName = "animal_wolf",
                iconName  = "wolf",
                details   = """
• Wolves rarely attack adult humans — children and livestock are at higher risk.
• If you encounter a wolf, stand tall and make yourself look large.
• Maintain eye contact and back away without turning your back.
• Do NOT run — wolves have a prey-chase instinct triggered by fleeing.
• Secure livestock in solid enclosures every night without fail.
• Wolves often operate in packs — one sighting means others are nearby.
• Guard dogs trained for livestock protection can be very effective.
• Avoid leaving young children unattended near forest-edge areas.
• Make noise (bells, singing) while walking through wolf territory.
• Report wolf presence and livestock kills to the forest department promptly.
                """.trimIndent()
            ),

            SafetyGuideItem(
                title     = "🦌 Spotted Deer (Chital) Safety",
                imageName = "animal_deer",
                iconName  = "deer",
                details   = """
• Deer appear gentle but male stags during rutting season are dangerous.
• An agitated stag can gore with its antlers with fatal force.
• Do not approach or attempt to feed deer — they can kick powerfully.
• Deer crossing roads cause many accidents — drive carefully in forest zones.
• During monsoon, deer move more and often cross roads at night — slow down.
• A startled deer may run into your path unpredictably.
• Never rescue or touch a spotted fawn — the mother is always nearby.
• Deer herds near villages indicate predators may also be close.
• Avoid harvesting crops after dark near forest edges during monsoon season.
• Fencing crops with thorny hedges reduces deer crop-raiding effectively.
                """.trimIndent()
            )
        )
    }
}
