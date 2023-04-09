package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.*;

public interface DataTaskBuilderFactory<E, K, D, F, V, S> {

    DataModelTypes<E, K, D, F, V, S> getDataModelTypes();

    CountEntitiesTask.Builder<?, ?, E, K, D, F, V, S> count();

    CreateEntityTask.Builder<?, ?, E, K, D, F, V, S> create();

    AddEntityTask.Builder<?, ?, E, K, D, F, V, S> add();

    CursorSearchAndCountTask.Builder<?, ?, E, K, D, F, V, S, SearchCursorAndCount<E>> cursorSearchAndCount();

    CursorSearchTask.Builder<?, ?, E, K, D, F, V, S, SearchCursor<E>> cursorSearch();

    PageSearchAndCountTask.Builder<?, ?, E, K, D, F, V, S, SearchPageAndCount<E>> pageSearchAndCount();

    PageSearchTask.Builder<?, ?, E, K, D, F, V, S, SearchPage<E>> pageSearch();

    RemoveEntitiesTask.Builder<?, ?, E, K, D, F, V, S> remove();

    RetrieveEntityTask.Builder<?, ?, E, K, D, F, V, S> retrieve();

    UpdateFieldsTask.Builder<?, ?, E, K, D, F, V, S> updateFields();

    UpdateEntityTask.Builder<?, ?, E, K, D, F, V, S> updateEntity();

}
