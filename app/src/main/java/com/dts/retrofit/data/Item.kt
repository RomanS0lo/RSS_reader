package com.dts.retrofit.data

import android.os.Parcel
import android.os.Parcelable
import com.dts.retrofit.data.entity.NewsEntity
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "category", required = false)
    var category: String? = null,

    @field:Element(name = "author", required = false)
    var author: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "guid", required = false)
    var guid: String? = null,

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null
) : Parcelable {

    val isValid get() = title != null && link != null && category != null && author != null && description != null && guid != null && pubDate != null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeString(category)
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeString(guid)
        parcel.writeString(pubDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {

        fun create(item: NewsEntity): Item {
            return Item(
                item.title,
                item.link,
                item.category,
                item.author,
                item.description,
                item.guid,
                item.pubDate
            )
        }

        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
