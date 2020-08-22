package iv.nakonechnyi.giftenor

import iv.nakonechnyi.giftenor.data.GifInfo
import kotlin.random.Random

class TestHelper {

    companion object{

        fun getListOfTestedGifInfo(): List<GifInfo> {
            val list = mutableListOf<GifInfo>()
            for (i in 0..Random.nextInt(10, 20)){
                val gifInfo = GifInfo(
                    id = i.toString(),
                    created = Random.nextDouble(),
                    title = "wolf",
                    preview = "https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw",
                    url = "https://media.tenor.com/images/a4239487938c5b1daa7615c657f2bf89/tenor.gif"
                )
                list.add(gifInfo)
            }
            return list
        }

        fun compareCollections(first: Collection<GifInfo>, second: Collection<GifInfo>) =
            first.containsAll(second) && second.containsAll(first)

    }

}