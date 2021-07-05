package raf.rs.WEB_Projekat.repositories.tag;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Tag;

import java.util.List;

public interface TagRepository {
    public Tag addTag(Tag tag);
    public List<Tag> allTags();
    public Tag findtag(Integer id);
    public void deleteTag(Integer id);
}
