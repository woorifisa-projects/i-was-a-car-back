package xyz.iwasacar.api.domain.roles.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberRole_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberRole_Pk extends BeanPath<MemberRole.Pk> {

    private static final long serialVersionUID = 16852943L;

    public static final QMemberRole_Pk pk = new QMemberRole_Pk("pk");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public QMemberRole_Pk(String variable) {
        super(MemberRole.Pk.class, forVariable(variable));
    }

    public QMemberRole_Pk(Path<? extends MemberRole.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberRole_Pk(PathMetadata metadata) {
        super(MemberRole.Pk.class, metadata);
    }

}

