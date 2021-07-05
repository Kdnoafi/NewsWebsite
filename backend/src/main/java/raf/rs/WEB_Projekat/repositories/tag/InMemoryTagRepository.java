package raf.rs.WEB_Projekat.repositories.tag;

import raf.rs.WEB_Projekat.entities.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryTagRepository implements TagRepository {

    private static List<Tag> tags = new CopyOnWriteArrayList<>();

    public InMemoryTagRepository() {}

    @Override
    public Tag addTag(Tag tag) {
        int id = tags.size();
        tag.setId(id);
        tags.add(Math.toIntExact(id), tag);

        return tag;
    }

    @Override
    public List<Tag> allTags() {
        return null;
    }

    @Override
    public Tag findtag(Integer id) {
        return null;
    }

    @Override
    public void deleteTag(Integer id) {

    }
}
