package nikitagorbatko.fojin.test.reviews.ui.entities

import nikitagorbatko.fojin.test.reviews.entity.Critic

class CriticUi(
    override val displayName: String?,
    override val sortName: String?,
    override val status: String?,
    override val bio: String?,
    override val seoName: String?,
    override val resourceType: String?,
    override val resourceSrc: String?,
    override val resourceHeight: Int?,
    override val resourceWidth: Int?,
    override val resourceCredit: String?
) : Critic