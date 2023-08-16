package xyz.iwasacar.api.domain.roles.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRole is a Querydsl query type for MemberRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRole extends EntityPathBase<MemberRole> {

    private static final long serialVersionUID = 1837014202L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRole memberRole = new QMemberRole("memberRole");

    public final QMemberRole_Pk id;

    public final xyz.iwasacar.api.domain.members.entity.QMember member;

    public final QRole role;

    public QMemberRole(String variable) {
        this(MemberRole.class, forVariable(variable), INITS);
    }

    public QMemberRole(Path<? extends MemberRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRole(PathMetadata metadata, PathInits inits) {
        this(MemberRole.class, metadata, inits);
    }

    public QMemberRole(Class<? extends MemberRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QMemberRole_Pk(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new xyz.iwasacar.api.domain.members.entity.QMember(forProperty("member")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

